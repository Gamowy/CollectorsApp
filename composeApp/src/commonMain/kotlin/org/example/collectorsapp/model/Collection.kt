package org.example.collectorsapp.model

import androidx.compose.ui.graphics.ImageBitmap

data class Collection(
    val id: String,
    val name: String,
    val description: String?,
    val imageBitmap: ImageBitmap?,
    val items: List<Item>,
    val category: CollectionCategory
)