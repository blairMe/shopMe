package bfa.blair.shopme.repository

import androidx.lifecycle.LiveData
import bfa.blair.shopme.data.ProductsDao
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.model.room.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRoomRepository @Inject constructor(private val productsDao: ProductsDao) {

    // Cart
    fun getCart() : Flow<List<Cart>> = productsDao.getCart()
    suspend fun insertProduct(cart: Cart) = productsDao.insertProduct(cart)
    suspend fun getCartItemById(title : String) = productsDao.getCartItemById(title)
    suspend fun updateCart(cart: Cart) = productsDao.updateCart(cart)
    suspend fun deleteAllCartItems() = productsDao.deleteAllCartItems()
    suspend fun deleteCartItem(cart : Cart) = productsDao.deleteCartItem(cart)

    // Favorite
    fun getFavorite() : LiveData<List<Favorite>> = productsDao.getFavorite()
    suspend fun insertFavorite(favorite: Favorite) = productsDao.insertFavorite(favorite)
    suspend fun getFavoriteItemById(title : String) = productsDao.getFavoriteItemById(title)
    suspend fun updateFavorites(favorite: Favorite) = productsDao.updateFavorites(favorite)
    suspend fun deleteAllFavoriteItems() = productsDao.deleteAllFavoriteItems()
    suspend fun deleteFavoriteItem(favorite: Favorite) = productsDao.deleteFavoriteItem(favorite)


}