package bfa.blair.shopme.data

import androidx.room.*
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.model.room.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    // Cart
    @Query("SELECT * from cart_tbl")
    fun getCart(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(cart: Cart)

    @Query("SELECT * from cart_tbl where title =:title")
    suspend fun getCartItemById(title: String): Cart

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCart(cart: Cart)

    @Query("DELETE from cart_tbl")
    suspend fun deleteAllCartItems()

    @Delete
    suspend fun deleteCartItem(cart: Cart)

    // Favorite
    @Query("SELECT * from favorite_tbl")
    fun getFavorite(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * from favorite_tbl where title =:title")
    suspend fun getFavoriteItemById(title: String): Favorite

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorites(favorite: Favorite)

    @Query("DELETE from favorite_tbl")
    suspend fun deleteAllFavoriteItems()

    @Delete
    suspend fun deleteFavoriteItem(favorite: Favorite)


}


