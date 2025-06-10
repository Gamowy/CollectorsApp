package org.example.collectorsapp.ui.views.ai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ai_action_item_proposal_description
import kotlinproject.composeapp.generated.resources.ai_action_predict_value_change
import kotlinproject.composeapp.generated.resources.ai_action_propose_items
import kotlinproject.composeapp.generated.resources.ai_action_propose_marketplaces
import kotlinproject.composeapp.generated.resources.ai_action_propose_marketplaces_description
import kotlinproject.composeapp.generated.resources.ai_action_value_change_description
import kotlinproject.composeapp.generated.resources.chart_line_variant
import kotlinproject.composeapp.generated.resources.currency_usd
import kotlinproject.composeapp.generated.resources.lightbulb_question_outline
import org.example.collectorsapp.model.AiActions
import org.example.collectorsapp.ui.components.PopupDialog
import org.example.collectorsapp.viewmodels.AiAssistViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

data class AssistChipContent(val label: StringResource, val description: StringResource, val icon : DrawableResource, val action: AiActions)

val aiAssistChips = listOf(
    AssistChipContent(
        label = Res.string.ai_action_propose_marketplaces,
        description = Res.string.ai_action_propose_marketplaces_description,
        icon = Res.drawable.currency_usd,
        action = AiActions.PROPOSE_MARKETPLACES
    ),
    AssistChipContent(
        label = Res.string.ai_action_propose_items,
        description = Res.string.ai_action_item_proposal_description,
        icon = Res.drawable.lightbulb_question_outline,
        action = AiActions.PROPOSE_NEW_ITEMS
    ),
    AssistChipContent(
        label = Res.string.ai_action_predict_value_change,
        description = Res.string.ai_action_value_change_description,
        icon = Res.drawable.chart_line_variant,
        action = AiActions.PREDICT_VALUE_CHANGE
    ),
)

@Composable
fun AiAssistView(
    viewModel: AiAssistViewModel,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.showError) {
        PopupDialog(
            popUpText = state.errorMessage ?: "Error while handling request",
            onDismiss = {
                viewModel.dismissError()
            },
            onConfirm = {
                viewModel.dismissError()
            },
            isErrorDialog = true)
    }

    if(state.selectedAiAction != null && state.targetCollectionId != null && !state.isAwaitingResponse) {
        viewModel.useAiAction()
    }

    Column(verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            if(state.isAwaitingResponse) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(120.dp)
                        .align(Alignment.Center)
                )
            }
            else {
                Text(
                    text = state.response ?: "",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (chip in aiAssistChips) {
                AssistChip(
                    label = {
                        Text(stringResource(chip.label))
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(chip.icon),
                            contentDescription = stringResource(chip.description),
                        )
                    },
                    onClick = {
                        viewModel.setAiAction(chip.action)
                        onActionClick()
                    },
                    enabled = !state.isAwaitingResponse
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}