package bfa.blair.shopme.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bfa.blair.shopme.model.network.Product
import bfa.blair.shopme.repository.Repository
import bfa.blair.shopme.utils.DataorException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val productsLiveData = MutableLiveData<DataorException<Product, Boolean, Exception>>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                productsLiveData.value = repository.getProducts()
            } catch (e : Exception) {
                Log.i("the message", e.message.toString())
            }
        }
    }
}