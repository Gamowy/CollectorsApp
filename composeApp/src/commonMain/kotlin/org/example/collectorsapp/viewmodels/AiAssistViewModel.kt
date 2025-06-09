package org.example.collectorsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import dev.shreyaspatil.ai.client.generativeai.type.Content
import dev.shreyaspatil.ai.client.generativeai.type.content
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.AiActions
import org.example.collectorsapp.ui.views.ai.AiAssistViewState

class AiAssistViewModel(private val repository: CollectionDatabase, private val modelName: String) : ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemDao = repository.getItemsDao()
    private var settingsDao = repository.getUserSettingsDao()

    private var aiRequest: Job? = null

    private var _state = MutableStateFlow(AiAssistViewState())
    val state = _state.asStateFlow()

    fun useAiAction(action: AiActions?, collectionId: Long?) {
        try {
            _state.update {
                it.copy(
                    selectedAiAction = action,
                    targetCollectionId = collectionId,
                    response = null,
                    errorMessage = null,
                    isAwaitingResponse = true,
                    showError = false
                )
            }

            if (action != null && collectionId != null) {
                viewModelScope.launch {
                    val geminiApiKey = settingsDao.getApiKey()
                    val currency = settingsDao.getCurrency()
                    val collectionValue = itemDao.getTotalValueByCollectionId(collectionId)

                    if (geminiApiKey.isBlank()) {
                        cancelAndShowError("No gemini API key provided. Make sure to provide valid API key in settings tab")
                        this.cancel()
                    }

                    val generativeModel = GenerativeModel(
                        modelName = modelName,
                        apiKey = geminiApiKey
                    )

                    val inputContent = content {
                        text(action.prompt)
                        text("$collectionValue $currency")
                    }

                    aiRequest = launch {
                        try {
                            withTimeout(15_000) {
                                _state.update {
                                    it.copy(
                                        selectedAiAction = it.selectedAiAction,
                                        targetCollectionId = it.targetCollectionId,
                                        response = null,
                                        errorMessage = null,
                                        isAwaitingResponse = true,
                                        showError = false
                                    )
                                }
                                generateResponse(generativeModel, inputContent)
                            }
                        }
                        catch (e: Exception) {
                            cancelAndShowError("Failed to generate response for given request. Check your internet connection and make sure your API key is valid.")
                            this.cancel()
                        }
                    }
                }
            } else {
                cancelAndShowError("Action or target collection not selected")

            }
        }
        catch  (e: Exception) {
            cancelAndShowError("Failed to generate response for given request")
        }
    }

    private suspend fun generateResponse(model: GenerativeModel, input: Content) {
        val responseText = model.generateContent(input).text
        if (responseText != null) {
            _state.update {
                it.copy(
                    selectedAiAction = null,
                    targetCollectionId = null,
                    response = responseText,
                    errorMessage = null,
                    isAwaitingResponse = false,
                    showError = false
                )
            }
        }
        else {
            cancelAndShowError("Failed to generate response for given request")
        }
    }

    private fun cancelAndShowError(error: String) {
        aiRequest?.cancel()
        _state.update {
            it.copy(
                selectedAiAction = null,
                targetCollectionId = null,
                response = null,
                errorMessage = error,
                isAwaitingResponse = false,
                showError = true
            )
        }
    }

    fun dismissError() {
        _state.update {
            it.copy(
                selectedAiAction = null,
                targetCollectionId = null,
                response = null,
                errorMessage = null,
                isAwaitingResponse = false,
                showError = false
            )
        }
    }
}