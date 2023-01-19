package bfa.blair.shopme.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bfa.blair.shopme.model.room.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Insert
    suspend fun insertProduct(cart: Cart)

    @Query("SELECT * from cart_tbl")
    fun getCart() : Flow<List<Cart>>


}


