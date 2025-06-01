package org.example.collectorsapp.ui.views.collections

data class CollectionsListState(
    val collectionsList: List<CollectionCardState> = emptyList(),
    val searchQuery: String = "",
    val searchResultsList: List<CollectionCardState> = emptyList(),
)