package org.example.collectorsapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.Condition
import org.example.collectorsapp.model.Item


class ItemDetailsViewmodel(private val collectionId: Long, private val itemId: Long, private val repository : CollectionDatabase) : ViewModel() {
    private var itemsDao = repository.getItemsDao()

    private var _item = MutableStateFlow(Item(itemId, collectionId, "", "", condition = Condition.New))
    val item = _item.asStateFlow()

    init {
        viewModelScope.launch {
            _item.update {
                itemsDao.getItemById(itemId) ?: it
            }
            itemsDao.getItemsByCollectionId(collectionId).collectLatest {
                _item.update {
                    itemsDao.getItemById(itemId) ?: it
                }
            }
        }
    }

    fun deleteItem() {
        viewModelScope.launch {
            itemsDao.delete(item.value)
        }
    }
}