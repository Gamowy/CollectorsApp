package org.example.collectorsapp.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.model.ItemsCollection

class CollectionDetailViewModel(private val collectionId: Long, private val repository: CollectionDatabase): ViewModel() {
    private var collectionDao = repository.getCollectionDao()
    private var itemsDao = repository.getItemsDao()
    
    private var _collection = MutableStateFlow<ItemsCollection?>(null)
    val collection = _collection.asStateFlow()



    
    init {
        viewModelScope.launch {
            _collection.value = collectionDao.getCollectionById(collectionId)
        }
    }



}