package org.example.collectorsapp.model

enum class AiActions(val prompt: String) {
    PROPOSE_MARKETPLACES(
        "Based on name, description and category of my items collection, propose online websites where i can sell or trade this collection. Your answer doesn't need to be long. Don't use '*' character in your response."
    ),
    PROPOSE_NEW_ITEMS(""),
    PREDICT_VALUE_CHANGE("")
}