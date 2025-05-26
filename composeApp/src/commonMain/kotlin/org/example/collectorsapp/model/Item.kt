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
    @ColumnInfo(name = "item_name") var name: String,
    @ColumnInfo(name = "item_description") var description: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var imageBitmap: ByteArray? = null,
    @ColumnInfo(name = "item_value") var estimatedValue: Double? = null,
    @ColumnInfo(name = "item_condition") var condition: Condition
) {

}
