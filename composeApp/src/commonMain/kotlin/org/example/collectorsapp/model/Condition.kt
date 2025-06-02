package org.example.collectorsapp.model

import kotlinx.serialization.Serializable

@Serializable
enum class Condition {
    New,
    Excellent,
    Good,
    Fair,
    Poor,
    Damaged,
}