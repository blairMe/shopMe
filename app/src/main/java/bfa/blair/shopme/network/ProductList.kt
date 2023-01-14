package bfa.blair.shopme.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductList(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
) : Parcelable
