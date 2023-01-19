package bfa.blair.shopme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.model.room.Favorite

@Database(entities = [Cart::class, Favorite::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao
}