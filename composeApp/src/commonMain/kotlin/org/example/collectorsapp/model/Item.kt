package org.example.collectorsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Suppress("ArrayInDataClass")
@Entity(foreignKeys = [
    ForeignKey(
        entity = ItemsCollection::class,
        parentColumns = ["collectionId"],
        childColumns = ["collection_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    ),
])
data class Item(
    @PrimaryKey(autoGenerate = true) val itemId: Long = 0L,
    @ColumnInfo(name = "collection_id", index = true) val collectionId: Long,
    @ColumnInfo(name = "item_name") val name: String,
    @ColumnInfo(name = "item_description") val description: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val imageBitmap: ByteArray?,
    @ColumnInfo(name = "item_value") val estimatedValue: Double?,
    @ColumnInfo(name = "item_condition") val condition: Condition
) {

}
