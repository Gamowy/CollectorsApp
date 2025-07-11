package org.example.collectorsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase

import org.example.collectorsapp.model.ItemsCollection
import org.example.collectorsapp.ui.views.collections.CollectionCardState
import org.example.collectorsapp.ui.views.collections.CollectionsListState

class CollectionsListViewModel(private val repository: CollectionDatabase) : ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemsDao = repository.getItemsDao()

    private var _state = MutableStateFlow(CollectionsListState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            collectionDao.getAllCollections().collectLatest { collections ->
                val list = collections.map { collection ->
                    CollectionCardState(collection, getCollectionValue(collection.collectionId))
                }
                _state.update {
                    it.copy(
                        collectionsList = list,
                        searchQuery = it.searchQuery,
                        searchResultsList = list
                    )
                }
            }
        }
    }

    fun upsertCollection(collection: ItemsCollection) {
        viewModelScope.launch {
            collectionDao.upsert(collection)
        }
    }

    fun deleteCollectionById(collectionId: Long) {
        viewModelScope.launch {
            val collection = collectionDao.getCollectionById(collectionId)
            if (collection != null) {
                collectionDao.delete(collection)
            }
        }
    }

    fun getCollectionById(collectionId: Long): CollectionCardState? {
        return _state.value.collectionsList.find { it.collection.collectionId == collectionId }
    }

    fun getAllCollectionsSortedByNameAsc(){
        viewModelScope.launch {
            collectionDao.getAllCollectionsSortedByNameAsc().collectLatest { collections ->
                val list = collections.map { collection ->
                    CollectionCardState(collection, getCollectionValue(collection.collectionId))
                }
                _state.update {
                    it.copy(
                        collectionsList = list,
                        searchResultsList = list
                    )
                }
            }
        }
    }

    fun getAllCollectionsSortedByNameDesc(){
        viewModelScope.launch {
            collectionDao.getAllCollectionsSortedByNameDesc().collectLatest { collections ->
                val list = collections.map { collection ->
                    CollectionCardState(collection, getCollectionValue(collection.collectionId))
                }
                _state.update {
                    it.copy(
                        collectionsList = list,
                        searchResultsList = list
                    )
                }
            }
        }
    }

    fun getAllCollectionsSortedByCategoryAsc(){
        viewModelScope.launch {
            collectionDao.getAllCollectionsSortedByCategoryAsc().collectLatest { collections ->
                val list = collections.map { collection ->
                    CollectionCardState(collection, getCollectionValue(collection.collectionId))
                }
                _state.update {
                    it.copy(
                        collectionsList = list,
                        searchResultsList = list
                    )
                }
            }
        }
    }

    fun getAllCollectionsSortedByCategoryDesc(){
        viewModelScope.launch {
            collectionDao.getAllCollectionsSortedByCategoryDesc().collectLatest { collections ->
                val list = collections.map { collection ->
                    CollectionCardState(collection, getCollectionValue(collection.collectionId))
                }
                _state.update {
                    it.copy(
                        collectionsList = list,
                        searchResultsList = list
                    )
                }
            }
        }
    }

    fun searchCollections(query: String) {
        _state.update {
            it.copy (
                collectionsList = it.collectionsList,
                searchQuery = query,
                searchResultsList = it.collectionsList.filter { cardState ->
                    cardState.collection.name.contains(query, ignoreCase = true) ||
                            cardState.collection.description?.contains(query, ignoreCase = true) == true ||
                            cardState.collection.category.name.contains(query, ignoreCase = true)
                }
            )
        }
    }

    private suspend fun getCollectionValue(collectionId: Long): Double  {
        return itemsDao.getTotalValueByCollectionId(collectionId) ?: 0.0
    }

    fun sortByValueAsc() {
        _state.update { it.copy(searchResultsList = it.searchResultsList.sortedBy { state -> state.collectionValue }) }
    }

    fun sortByValueDesc() {
        _state.update { it.copy(searchResultsList = it.searchResultsList.sortedByDescending { state -> state.collectionValue }) }
    }
}