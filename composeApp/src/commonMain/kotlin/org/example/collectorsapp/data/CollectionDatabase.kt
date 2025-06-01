package org.example.collectorsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.collectorsapp.model.Item
import org.example.collectorsapp.model.ItemsCollection

@Database(entities = [ItemsCollection::class, Item::class], version = 1, exportSchema = false)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun getCollectionDao(): ItemsCollectionDao
    abstract fun getItemsDao(): ItemsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object CollectionDatabaseConstructor : RoomDatabaseConstructor<CollectionDatabase> {
    override fun initialize(): CollectionDatabase
}

fun getCollectionDatabase(builder: RoomDatabase.Builder<CollectionDatabase>): CollectionDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}