package bfa.blair.shopme.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.model.room.ProductsTypeConverter

@Database(entities = [Cart::class, Favorite::class], version = 1, exportSchema = false)
@TypeConverters(ProductsTypeConverter::class)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao
}