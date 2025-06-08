package org.example.collectorsapp.ui.views.collections

import org.example.collectorsapp.model.CollectionCategory
import org.example.collectorsapp.model.Item
import org.example.collectorsapp.model.ItemsCollection

data class CollectionDetailState(
    val collection : ItemsCollection = ItemsCollection(
        name = "Unknown Collection",
        category = CollectionCategory.Anything
    ),
    val collectionValue: Double = 0.0,
    val items: List<Item> = emptyList(),
    val searchQuery: String = "",
    val searchResultsList: List<Item> = emptyList(),
    val currency : String = "USD"
)