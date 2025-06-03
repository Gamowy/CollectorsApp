package org.example.collectorsapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.delete_data_confirmation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.Currencies
import org.example.collectorsapp.AppState
import org.example.collectorsapp.ui.components.PopupDialog
import org.jetbrains.compose.resources.stringResource

data class SettingsUiState(
    val currencyUI: Currencies = Currencies.USD,
    val currencySymbolUI: String = "$",
    val apiKeyUI: String? = null
)

class SettingsViewModel(private val repository: CollectionDatabase) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onApiKeyChange(newKey: String) {
        _uiState.value = _uiState.value.copy(apiKeyUI = newKey)
        AppState.apiKey = newKey
    }

    fun onCurrencyChange(currency: Currencies, symbol: String) {
        _uiState.value = _uiState.value.copy(currencyUI = currency)
        _uiState.value = uiState.value.copy(currencySymbolUI = symbol)
        AppState.currency = currency
        AppState.currencySymbol = symbol
    }

    fun clearAppData() {
        viewModelScope.launch {
            repository.getItemsDao().deleteAllItems()
            repository.getCollectionDao().deleteAllCollections()
        }
    }
}