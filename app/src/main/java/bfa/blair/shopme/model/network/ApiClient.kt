package bfa.blair.shopme.model.network

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object ApiClient {
    // https://fakestoreapi.com/products
    private var BASE_URL = "https://fakestoreapi.com/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}

//data class ProductsResponse(
//    val id : String,
//    val title : String,
//    val price : String,
//    val category : String,
//    val description : String,
//    val image : String
//)

interface ApiService{
    @GET("products")
    suspend fun fetchProducts() : ProductResponse
}