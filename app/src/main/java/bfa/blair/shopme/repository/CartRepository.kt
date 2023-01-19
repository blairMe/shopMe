package bfa.blair.shopme.repository

import bfa.blair.shopme.data.ProductsDao
import bfa.blair.shopme.data.ProductsDatabase
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.model.room.Cart
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(private val productsDao: ProductsDao) {
    fun getCart() : Flow<List<Cart>> = productsDao.getCart()

    suspend fun insertProduct(cart: Cart) {
        productsDao.insertProduct(cart)
    }
}