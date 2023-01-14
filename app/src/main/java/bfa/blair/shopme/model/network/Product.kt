package bfa.blair.shopme.model.network

data class Product(
    val limit: Int = 100,
    val products: List<ProductList>,
    val skip: Int,
    val total: Int
)