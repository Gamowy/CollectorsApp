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

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_name ASC")
    fun getAllItemsSortedByNameAsc(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_name DESC")
    fun getAllItemsSortedByNameDesc(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_value ASC")
    fun getAllItemsSortedByValueAsc(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_value DESC")
    fun getAllItemsSortedByValueDesc(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_condition ASC")
    fun getAllItemsSortedByConditionAsc(collectionId: Long): Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE collection_id = :collectionId ORDER BY item_condition DESC")
    fun getAllItemsSortedByConditionDesc(collectionId: Long): Flow<List<Item>>

    @Query("DELETE FROM Item")
    suspend fun deleteAllItems()
}