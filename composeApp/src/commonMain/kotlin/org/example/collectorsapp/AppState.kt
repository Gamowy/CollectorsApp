package org.example.collectorsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.collectorsapp.model.Currencies

object AppState {
    var currency by mutableStateOf(Currencies.USD)
    var currencySymbol by mutableStateOf(Currencies.USD.symbol)

    var apiKey by mutableStateOf<String?>(null)
}