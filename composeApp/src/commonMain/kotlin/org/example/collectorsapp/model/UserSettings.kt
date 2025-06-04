package org.example.collectorsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class UserSettings(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "currency")val currency: String = "USD",
    @ColumnInfo(name = "api_key")val apiKey: String = ""
)