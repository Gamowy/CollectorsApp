package org.example.collectorsapp.ui.views.collections

import org.example.collectorsapp.model.ItemsCollection

data class CollectionsListState(
    val collectionsList: List<CollectionState> = emptyList(),
    val searchQuery: String = "",
    val searchResultsList: List<CollectionState> = emptyList(),
)