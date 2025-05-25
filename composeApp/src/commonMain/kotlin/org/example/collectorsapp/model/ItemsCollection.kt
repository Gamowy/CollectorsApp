package org.example.collectorsapp.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("ArrayInDataClass")
@Entity
data class ItemsCollection(
    @PrimaryKey(autoGenerate = true) val collectionId: Long = 0L,
    @ColumnInfo(name = "collection_name") val name: String,
    @ColumnInfo(name = "collection_description") val description: String?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val image: ByteArray? = null,
    @ColumnInfo(name = "collection_category")  val category: CollectionCategory
) {

}