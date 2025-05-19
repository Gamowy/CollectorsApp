package org.example.collectorsapp.model

import androidx.compose.ui.graphics.ImageBitmap

data class Item(
    val id: String,
    val name: String,
    val description: String?,
    val imageBitmap: ImageBitmap?,
    val estimatedValue: Double?,
    val condition: Condition
)
