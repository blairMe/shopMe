package bfa.blair.shopme.model.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "brand")
    val brand: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "discountPercentage")
    val discountPercentage: Double,
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "price")
    val price: Int,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "stock")
    val stock: Int,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "title")
    val title: String
) : Parcelable

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "products")
    val result : List<Product>
)