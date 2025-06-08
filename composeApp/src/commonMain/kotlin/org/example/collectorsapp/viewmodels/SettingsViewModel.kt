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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.data.UserSettingsDao
import org.example.collectorsapp.model.Currencies
import org.example.collectorsapp.ui.components.PopupDialog
import org.jetbrains.compose.resources.stringResource
import org.example.collectorsapp.ui.views.settings.SettingsState

class SettingsViewModel(private val repository: CollectionDatabase) : ViewModel() {
    private val collectionDao = repository.getCollectionDao()
    private val userSettingsDao = repository.getUserSettingsDao()

    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val apiKey =  userSettingsDao.getApiKey()
            val currency = userSettingsDao.getCurrency()
            _uiState.update {
                it.copy (
                    apiKeyUI = apiKey,
                    currencyUI = Currencies.valueOf(currency)
                )
            }
        }
    }

    fun onApiKeyChange(newKey: String) {
        viewModelScope.launch {
            userSettingsDao.updateApiKey(newKey)
            _uiState.update {
                it.copy(
                    apiKeyUI = newKey,
                    currencyUI = it.currencyUI
                )
            }
        }
    }

    fun onCurrencyChange(currency: Currencies) {
        viewModelScope.launch {
            userSettingsDao.updateCurrency(currency.name)
            _uiState.update {
                it.copy(
                    apiKeyUI = it.apiKeyUI,
                    currencyUI = currency
                )
            }
        }
    }

    fun clearAppData() {
        viewModelScope.launch {
            repository.getCollectionDao().deleteAllCollections()
        }
    }
}