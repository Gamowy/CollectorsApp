package org.example.collectorsapp.ui.views.settings
import org.example.collectorsapp.model.Currencies

data class SettingsState(
    var currencyUI: Currencies = Currencies.USD,
    var apiKeyUI: String = ""
)