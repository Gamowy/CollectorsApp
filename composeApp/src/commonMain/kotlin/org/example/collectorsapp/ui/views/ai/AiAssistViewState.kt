package org.example.collectorsapp.ui.views.ai

import kotlinx.coroutines.Job
import org.example.collectorsapp.model.AiActions

data class AiAssistViewState(
    val selectedAiAction : AiActions? = null,
    val targetCollectionId: Long? = null,
    val response: String? = null,
    var errorMessage: String? = null,
    val isAwaitingResponse: Boolean = false,
    val showError: Boolean = false
)