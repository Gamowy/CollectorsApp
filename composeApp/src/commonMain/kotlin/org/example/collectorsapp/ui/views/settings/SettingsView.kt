package org.example.collectorsapp.ui.views.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinproject.composeapp.generated.resources.Res
import org.example.collectorsapp.model.Currencies
import org.example.collectorsapp.ui.components.DropdownMenu
import org.jetbrains.compose.resources.stringResource
import org.example.collectorsapp.viewmodels.SettingsViewModel
import kotlinproject.composeapp.generated.resources.text_gemini_api_key
import kotlinproject.composeapp.generated.resources.label_gemini_api_key
import kotlinproject.composeapp.generated.resources.text_currency_change
import kotlinproject.composeapp.generated.resources.dropdown_currency_label
import kotlinproject.composeapp.generated.resources.text_clear_all_data
import kotlinproject.composeapp.generated.resources.button_clear_data
import kotlinproject.composeapp.generated.resources.delete_data_confirmation
import org.example.collectorsapp.ui.components.PopupDialog


@Composable
fun SettingsView(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
    ) {
    val state: SettingsState by viewModel.uiState.collectAsStateWithLifecycle()

    var showAlertDialog by remember { mutableStateOf(false) }

    if (showAlertDialog) {
        PopupDialog(popUpText = stringResource(Res.string.delete_data_confirmation),
            onDismiss = {
                showAlertDialog = false
            },
            onConfirm = {
                showAlertDialog = false
                viewModel.clearAppData()
            })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.text_gemini_api_key),
                modifier = modifier,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = state.apiKeyUI,
                onValueChange = {
                    viewModel.onApiKeyChange(it)
                },
                label = { Text(stringResource(Res.string.label_gemini_api_key)) },
                modifier = modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(Res.string.text_currency_change),
                modifier = modifier,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            DropdownMenu(
                label = stringResource(Res.string.dropdown_currency_label),
                options = Currencies.entries.map { it.name },
                selectedOption = state.currencyUI.name,
                onOptionSelected = {
                    viewModel.onCurrencyChange(currency = Currencies.valueOf(it))
                                   },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp)
            )

            Text(
                text = stringResource(Res.string.text_clear_all_data),
                modifier = modifier,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    showAlertDialog = true
                },
                modifier = modifier
                    .height(50.dp)
                    .width(170.dp)
            ) {
                Text(
                    text = stringResource(Res.string.button_clear_data)
                )
            }
        }
    }
}