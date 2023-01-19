package bfa.blair.shopme.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tbl")
data class Favorite(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "discountPercentage")
    val discountPercentage: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "images")
    val images: ArrayList<String>,
)