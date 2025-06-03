package org.example.collectorsapp.model

import kotlinx.serialization.Serializable

@Serializable
enum class Currencies(val symbol: String) {
    USD("$"),
    EUR("€"),
    GBP("£"),
    PLN("zł"),
    JPY("¥"),
    AUD("A$"),
    CAD("C$"),
    CHF("CHF"),
    SEK("kr"),
    NZD("NZ$");
}