package org.example.collectorsapp.ui.views.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.Item

class CollectionDetailViewModel(private val collectionId: Long, private val repository: CollectionDatabase): ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemsDao = repository.getItemsDao()

    private var _state = MutableStateFlow(CollectionDetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val collection = collectionDao.getCollectionById(collectionId)
            val collectionValue = getCollectionValue(collectionId)

            itemsDao.getItemsByCollectionId(collectionId).collectLatest { items ->
                _state.update {
                    it.copy(
                        collection = collection,
                        collectionValue = collectionValue,
                        items = items
                    )
                }
            }
        }
    }

    fun upsertItem(item:  Item) {
        viewModelScope.launch {
            itemsDao.upsert(item)
        }
    }

    fun getItemById(itemId: Long) : Item? {
        return _state.value.items.find { it.itemId == itemId }
    }

    private suspend fun getCollectionValue(collectionId: Long): Double  {
        return itemsDao.getTotalValueByCollectionId(collectionId) ?: 0.0
    }
}