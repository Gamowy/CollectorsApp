package org.example.collectorsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.Item
import org.example.collectorsapp.ui.views.collections.CollectionDetailState

class CollectionDetailViewModel(private val collectionId: Long, private val repository: CollectionDatabase): ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemsDao = repository.getItemsDao()
    private var settingsDao = repository.getUserSettingsDao()

    private var _state = MutableStateFlow(CollectionDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val collection = collectionDao.getCollectionById(collectionId)
            itemsDao.getItemsByCollectionId(collectionId).collectLatest { items ->
                val currency = settingsDao.getCurrency()
                _state.update {
                    it.copy(
                        collection = collection ?: it.collection,
                        collectionValue = getCollectionValue(collectionId),
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items,
                        currency = currency
                    )
                }
            }
        }
    }

    fun upsertItem(item:  Item) {
        viewModelScope.launch {
            itemsDao.upsert(item)
            _state.update {
                it.copy(
                    collection = it.collection,
                    collectionValue = getCollectionValue(collectionId),
                    items = it.items,
                    searchQuery = it.searchQuery,
                    searchResultsList = it.searchResultsList
                )
            }
            collectionDao.upsert(_state.value.collection)
        }
    }

    suspend fun getItemById(itemId: Long) : Item? {
        return itemsDao.getItemById(itemId)
    }

    fun searchItems(query: String) {
        _state.update {
            it.copy(
                collection = it.collection,
                collectionValue = it.collectionValue,
                items = it.items,
                searchQuery = query,
                searchResultsList = it.items.filter { item ->
                    item.name.contains(query, ignoreCase = true) ||
                    item.description?.contains(query, ignoreCase = true) == true ||
                    item.condition.name.contains(query, ignoreCase = true)
                }
            )
        }
    }

    private suspend fun getCollectionValue(collectionId: Long): Double  {
        return itemsDao.getTotalValueByCollectionId(collectionId) ?: 0.0
    }

    fun getAllItemsSortedByNameAsc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByNameAsc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }

    fun getAllItemsSortedByNameDesc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByNameDesc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }

    fun getAllItemsSortedByValueAsc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByValueAsc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }

    fun getAllItemsSortedByValueDesc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByValueDesc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }

    fun getAllItemsSortedByConditionAsc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByConditionAsc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }

    fun getAllItemsSortedByConditionDesc() {
        viewModelScope.launch {
            itemsDao.getAllItemsSortedByConditionDesc().collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = it.collection,
                        collectionValue = it.collectionValue,
                        items = items,
                        searchQuery = it.searchQuery,
                        searchResultsList = items
                    )
                }
            }
        }
    }
}