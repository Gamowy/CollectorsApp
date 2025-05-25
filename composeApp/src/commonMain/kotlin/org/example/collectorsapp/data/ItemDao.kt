package org.example.collectorsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.collectorsapp.model.Item

@Dao
interface ItemDao {
    @Upsert
    suspend fun upsert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId")
    fun getItemsByCollectionId(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE itemId = :id")
    suspend fun getItemById(id: Long): Item
}