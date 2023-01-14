package bfa.blair.shopme.network

import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiClient {
    @GET(value = "products")
    suspend fun getProducts() : Product
}