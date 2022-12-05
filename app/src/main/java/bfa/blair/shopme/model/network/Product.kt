package bfa.blair.shopme.model.network

import com.squareup.moshi.Json

data class Product(
    @Json(name = "id")
    val id : String,
    @Json(name = "title")
    val title : String,
    @Json(name = "price")
    val price : String,
    @Json(name = "category")
    val category : String,
    @Json(name = "description")
    val description : String,
    @Json(name = "image")
    val image : String
    )

data class ProductResponse(
    @Json(name = "results")
    val result : List<Product>
)