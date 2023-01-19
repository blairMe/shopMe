package bfa.blair.shopme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.model.room.Cart

@Database(entities = [Cart::class], version = 2, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao
}