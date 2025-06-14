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

    @Query("SELECT * FROM Item ORDER BY item_name ASC")
    fun getAllItemsSortedByNameAsc(): Flow<List<Item>>

    @Query("SELECT * FROM Item ORDER BY item_name DESC")
    fun getAllItemsSortedByNameDesc(): Flow<List<Item>>

    @Query("SELECT * FROM Item ORDER BY item_value ASC")
    fun getAllItemsSortedByValueAsc(): Flow<List<Item>>

    @Query("SELECT * FROM Item ORDER BY item_value DESC")
    fun getAllItemsSortedByValueDesc(): Flow<List<Item>>

    @Query("SELECT * FROM Item ORDER BY item_condition ASC")
    fun getAllItemsSortedByConditionAsc(): Flow<List<Item>>

    @Query("SELECT * FROM Item ORDER BY item_condition DESC")
    fun getAllItemsSortedByConditionDesc(): Flow<List<Item>>

    @Query("DELETE FROM Item")
    suspend fun deleteAllItems()
}