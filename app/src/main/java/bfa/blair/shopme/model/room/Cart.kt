package bfa.blair.shopme.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_tbl")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "brand")
    val brand : String,
    @ColumnInfo(name = "description")
    val description : String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "discountPercentage")
    val discountPercentage: Double,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "images")
    val images: List<String> )
