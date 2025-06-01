package org.example.collectorsapp.ui.views.collections

import org.example.collectorsapp.model.ItemsCollection

data class CollectionCardState(
    val collection: ItemsCollection,
    val collectionValue: Double = 0.0,
)