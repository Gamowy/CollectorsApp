package org.example.collectorsapp.data

import androidx.room.Dao
import androidx.room.Query
import org.example.collectorsapp.model.Currencies

@Dao
interface UserSettingsDao {
    @Query ("SELECT currency FROM UserSettings LIMIT 1")
    suspend fun getCurrency(): String

    @Query ("SELECT api_key FROM UserSettings LIMIT 1")
    suspend fun getApiKey(): String

    @Query ("UPDATE UserSettings SET currency = :currency")
    suspend fun updateCurrency(currency: String)

    @Query ("UPDATE UserSettings SET api_key = :apiKey")
    suspend fun updateApiKey(apiKey: String)
}