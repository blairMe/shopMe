package bfa.blair.shopme.repository

import android.util.Log
import bfa.blair.shopme.model.network.ApiClient
import bfa.blair.shopme.model.network.Product
import bfa.blair.shopme.utils.DataorException
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiClient) {

    private val dataorException = DataorException<Product, Boolean, Exception>()

    suspend fun getProducts() : DataorException<Product, Boolean, Exception> {
        val response = try {
            dataorException.loading = true
            dataorException.data = apiService.getProducts()
            if(dataorException.data.toString().isNotEmpty()) {
                dataorException.loading = false
            } else {
                //TODO
            }
        } catch (e : Exception) {
            return DataorException(e = e)
        }
        Log.d("Response", "$response")
        return dataorException
    }
}