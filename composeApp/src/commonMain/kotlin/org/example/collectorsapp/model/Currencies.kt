package org.example.collectorsapp.model

import kotlinx.serialization.Serializable

@Serializable
enum class Currencies() {
    USD,
    EUR,
    GBP,
    PLN,
    JPY,
    AUD,
    CAD,
    CHF,
    SEK,
    NZD;
}