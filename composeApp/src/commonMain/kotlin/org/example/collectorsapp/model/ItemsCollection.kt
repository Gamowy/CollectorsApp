package org.example.collectorsapp.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("ArrayInDataClass")
@Entity
data class ItemsCollection(
    @PrimaryKey(autoGenerate = true) val collectionId: Long = 0L,
    @ColumnInfo(name = "collection_name") var name: String,
    @ColumnInfo(name = "collection_description") var description: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null,
    @ColumnInfo(name = "collection_category")  var category: CollectionCategory
) {

}