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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.AiActions
import org.example.collectorsapp.ui.views.ai.AiAssistViewState

class AiAssistViewModel(repository: CollectionDatabase, private val modelName: String) : ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemDao = repository.getItemsDao()
    private var settingsDao = repository.getUserSettingsDao()

    private var aiRequest: Job? = null

    private var _state = MutableStateFlow(AiAssistViewState())
    val state = _state.asStateFlow()

    fun setAiAction(action: AiActions) {
        _state.update {
            it.copy(
                selectedAiAction = action,
                targetCollectionId = null,
                header = it.header,
                response = it.response,
                errorMessage = it.errorMessage,
                isAwaitingResponse = it.isAwaitingResponse,
                showError = it.showError
            )
        }
    }

    fun setCollectionId(collectionId: Long) {
        _state.update {
            it.copy(
                selectedAiAction = it.selectedAiAction,
                targetCollectionId = collectionId,
                header = it.header,
                response = it.response,
                errorMessage = it.errorMessage,
                isAwaitingResponse = it.isAwaitingResponse,
                showError = it.showError
            )
        }
    }

    fun useAiAction() {
        try {
            val action = _state.value.selectedAiAction
            val collectionId = _state.value.targetCollectionId

            if (action != null && collectionId != null) {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            selectedAiAction = action,
                            targetCollectionId = collectionId,
                            header = null,
                            response = null,
                            errorMessage = null,
                            isAwaitingResponse = true,
                            showError = false
                        )
                    }

                    val geminiApiKey = settingsDao.getApiKey()

                    if (geminiApiKey.isBlank()) {
                        cancelAndShowError("No gemini API key provided. Make sure to provide valid API key in settings tab")
                        this.cancel()
                    }

                    val generativeModel = GenerativeModel(
                        modelName = modelName,
                        apiKey = geminiApiKey,
                    )

                    val inputContent = getRequestContent(action, collectionId)

                    if (inputContent != null) {
                        aiRequest = launch {
                            try {
                                withTimeout(15_000) {
                                    _state.update {
                                        it.copy(
                                            selectedAiAction = it.selectedAiAction,
                                            targetCollectionId = it.targetCollectionId,
                                            header = null,
                                            response = null,
                                            errorMessage = null,
                                            isAwaitingResponse = true,
                                            showError = false
                                        )
                                    }
                                    generateResponse(generativeModel, inputContent)
                                }
                            } catch (e: Exception) {
                                cancelAndShowError("Failed to generate response for given request. Check your internet connection and make sure your API key is valid.")
                                this.cancel()
                            }
                        }
                    }
                    else {
                        cancelAndShowError("Failed to generate response for given request")
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
        val response = model.generateContent(input).text?.replace("*", "")
        if (response != null) {
            _state.update {
                it.copy(
                    selectedAiAction = null,
                    targetCollectionId = null,
                    header = state.value.selectedAiAction?.name?.replace('_', ' ') ?: "",
                    response = response,
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

    private suspend fun getRequestContent(action: AiActions, collectionId: Long) : Content? {
        val collection = collectionDao.getCollectionById(collectionId)
        val items = itemDao.getItemsByCollectionId(collectionId).first()
        val currency = settingsDao.getCurrency()
        if (collection != null) {
            return when (action) {
                AiActions.PROPOSE_MARKETPLACES -> {
                    content {
                        text("${action.prompt}\n")
                        text("${collection.name}\n")
                        collection.description?.let {
                            text("$it\n")
                        }
                        text("${collection.category.name}\n")
                    }
                }
                AiActions.SUGGEST_NEW_ITEMS -> {
                    val itemsString = items.joinToString {it.name}
                    content {
                        text("${action.prompt}\n")
                        text(itemsString)
                    }
                }
                AiActions.PREDICT_VALUE_CHANGE -> {
                    val itemsString = items.joinToString { "${it.name}: ${it.estimatedValue}${currency}" }
                    content {
                        text("${action.prompt}\n")
                        text(itemsString)
                    }
                }
            }
        }
        else return null
    }

    private fun cancelAndShowError(error: String) {
        aiRequest?.cancel()
        _state.update {
            it.copy(
                selectedAiAction = null,
                targetCollectionId = null,
                header = null,
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
                header = null,
                response = null,
                errorMessage = null,
                isAwaitingResponse = false,
                showError = false
            )
        }
    }
}