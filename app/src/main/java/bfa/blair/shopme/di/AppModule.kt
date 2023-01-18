package bfa.blair.shopme.di

import android.content.Context
import androidx.room.Room
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
class AppModule {

    @Provides
    @Singleton
    fun provideProductsApi() : ApiClient {
        return Retrofit.Builder()
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) {
        Room.databaseBuilder(
            context,
            ProductsDatabase::class.java,
            "products_database")
            .fallbackToDestructiveMigration()
            .build()
    }


}