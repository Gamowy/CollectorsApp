package org.example.collectorsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.collectorsapp.model.Currencies
import org.example.collectorsapp.model.Item
import org.example.collectorsapp.model.ItemsCollection
import org.example.collectorsapp.model.UserSettings

@Database(entities = [ItemsCollection::class, Item::class, UserSettings::class], version = 1, exportSchema = false)
abstract class CollectionDatabase : RoomDatabase() {
    abstract fun getCollectionDao(): ItemsCollectionDao
    abstract fun getItemsDao(): ItemsDao
    abstract fun getUserSettingsDao(): UserSettingsDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object CollectionDatabaseConstructor : RoomDatabaseConstructor<CollectionDatabase> {
    override fun initialize(): CollectionDatabase
}

fun getCollectionDatabase(builder: RoomDatabase.Builder<CollectionDatabase>): CollectionDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .addCallback(InitializeDatabase())
        .build()
}
internal class InitializeDatabase : RoomDatabase.Callback() {
    override fun onOpen(connection : SQLiteConnection) {
        connection.apply {
            execSQL("INSERT OR IGNORE INTO UserSettings(currency, api_key) VALUES ('${Currencies.USD.name}', '${"1234"}')")
        }
    }
}

suspend fun CollectionDatabase.clearDatabase() {
    getCollectionDao().deleteAllCollections()
    getItemsDao().deleteAllItems()
}