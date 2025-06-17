package org.example.collectorsapp.model

enum class AiActions(val prompt: String) {
    PROPOSE_MARKETPLACES(
        "Based on name, description and category of my items collection, propose online websites where i can sell or trade this collection. Don't mention websites that aren't english or polish.. Your answer doesn't need to be long. Don't use '*' character in your response."),
    SUGGEST_NEW_ITEMS("Based on items in my collection listed below, propose new similar items i could add to my collection. Your answer doesn't need to be long. Explain shortly why i should add each item you propose. Don't use '*' character in your response."),
    PREDICT_VALUE_CHANGE("Based on items in my collection and their value, predict if value of my entire collection will increase or decrease overtime. Your answer doesn't need to be long. Explain your prediction. Don't use '*' character in your response."),
}