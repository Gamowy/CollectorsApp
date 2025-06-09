package org.example.collectorsapp.model

enum class AiActions(val prompt: String) {
    CURRENCY_CONVERSION(
        "Below i will give you a value with specified currency. Convert this value into following currencies: USD, EUR, GBP, JPY, AUD, CAD, CHF, SEK, NZD (skip conversion if currency is same)"
    ),
    PROPOSE_NEW_ITEMS(""),
    PREDICT_VALUE_CHANGE("")
}