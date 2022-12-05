package bfa.blair.shopme.model.repository

import bfa.blair.shopme.model.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getProducts() = apiService.fetchProducts()
}