package org.example.collectorsapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<CollectionDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("collection_database.db")

    return Room.databaseBuilder<CollectionDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}