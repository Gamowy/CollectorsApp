package org.example.collectorsapp.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase

import org.example.collectorsapp.model.ItemsCollection

class CollectionsViewModel(private val repository: CollectionDatabase) : ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var data : List<ItemsCollection> = emptyList()

    private var _collectionsList = MutableStateFlow(data)
    val collectionsList = _collectionsList

    init {
        viewModelScope.launch {
            collectionDao.getAllCollections().collectLatest { collections ->
                data = collections
                _collectionsList.value = data
            }
        }
    }

    fun searchCollections(query: String): Unit {
        _collectionsList.update {
            data.filter { collection ->
                collection.name.contains(query, ignoreCase = true) ||
                        collection.description?.contains(query, ignoreCase = true) == true ||
                        collection.category.name.contains(query, ignoreCase = true)
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
            collectionDao.delete(collection)
        }
    }

    fun getCollectionById(collectionId: Long): ItemsCollection? {
        return data.find { it.collectionId == collectionId }
    }
}