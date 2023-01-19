package bfa.blair.shopme.di

import android.content.Context
import androidx.room.Room
import bfa.blair.shopme.data.ProductsDao
import bfa.blair.shopme.data.ProductsDatabase
import bfa.blair.shopme.model.network.ApiClient
import bfa.blair.shopme.utils.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsDatabase(productsDatabase: ProductsDatabase) : ProductsDao
            = productsDatabase.productsDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : ProductsDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ProductsDatabase::class.java,
            "products_database")
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideProductsApi() : ApiClient {
        return Retrofit.Builder()
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

}