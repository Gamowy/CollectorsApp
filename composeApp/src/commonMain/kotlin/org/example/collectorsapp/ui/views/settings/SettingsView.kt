package org.example.collectorsapp.ui.views.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
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
import kotlinproject.composeapp.generated.resources.change_currency_description
import kotlinproject.composeapp.generated.resources.delete_data_confirmation
import kotlinproject.composeapp.generated.resources.gemini_link_description
import kotlinproject.composeapp.generated.resources.link_gemini
import kotlinproject.composeapp.generated.resources.link_gemini_text
import kotlinproject.composeapp.generated.resources.settings_gemini_description
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
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        ) {
            Text(
                text = stringResource(Res.string.text_gemini_api_key),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                buildAnnotatedString {
                    append(stringResource(Res.string.settings_gemini_description))
                    append(stringResource(Res.string.gemini_link_description))
                    withLink(
                        link = LinkAnnotation.Url(
                            url = stringResource(Res.string.link_gemini),
                            styles = TextLinkStyles(
                                style = SpanStyle(color = Color.Blue),
                                focusedStyle = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
                                hoveredStyle = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
                                pressedStyle = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
                            )
                        )
                    ) {
                        append(stringResource(Res.string.link_gemini_text))
                    } },
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign =  TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            OutlinedTextField(
                value = state.apiKeyUI,
                onValueChange = {
                    viewModel.onApiKeyChange(it)
                },
                label = { Text(stringResource(Res.string.label_gemini_api_key)) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(Res.string.text_currency_change),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = stringResource(Res.string.change_currency_description),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
            DropdownMenu(
                label = stringResource(Res.string.dropdown_currency_label),
                options = Currencies.entries.map { it.name },
                selectedOption = state.currencyUI.name,
                onOptionSelected = {
                    viewModel.onCurrencyChange(currency = Currencies.valueOf(it))
                                   },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp)
            )

            Text(
                text = stringResource(Res.string.text_clear_all_data),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    showAlertDialog = true
                },
                modifier = Modifier
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