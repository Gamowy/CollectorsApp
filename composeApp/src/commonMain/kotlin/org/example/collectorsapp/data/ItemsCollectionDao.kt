package org.example.collectorsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.collectorsapp.model.ItemsCollection

@Dao
interface ItemsCollectionDao {
    @Upsert
    suspend fun upsert(collection: ItemsCollection)

    @Delete
    suspend fun delete(collection: ItemsCollection)

    @Query("SELECT * FROM ItemsCollection")
    fun getAllCollections(): Flow<List<ItemsCollection>>

    @Query("SELECT * FROM ItemsCollection WHERE collectionId = :id")
    suspend fun getCollectionById(id: Long): ItemsCollection?
}