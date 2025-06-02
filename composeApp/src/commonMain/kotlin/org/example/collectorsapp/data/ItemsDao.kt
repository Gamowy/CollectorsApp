package org.example.collectorsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.collectorsapp.model.Item

@Dao
interface ItemsDao {
    @Upsert
    suspend fun upsert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query ("SELECT SUM(Item.item_value) FROM Item WHERE collection_id = :collectionId")
    suspend fun getTotalValueByCollectionId(collectionId: Long): Double?

    @Query("SELECT * FROM Item WHERE itemId = :id")
    suspend fun getItemById(id: Long): Item?

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId")
    fun getItemsByCollectionId(collectionId: Long): Flow<List<Item>>
}