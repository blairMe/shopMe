package bfa.blair.shopme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bfa.blair.shopme.model.network.ApiClient
import bfa.blair.shopme.model.network.Product
import bfa.blair.shopme.model.repository.Repository
import bfa.blair.shopme.ui.activities.ScreenState
import kotlinx.coroutines.launch

class ProductsViewModel(private val repository: Repository =
    Repository(ApiClient.apiService)) : ViewModel() {

    private val _productsLiveData = MutableLiveData<ScreenState<List<Product>?>>()
    val productsLiveData : LiveData<ScreenState<List<Product>?>>

    get() = _productsLiveData

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        _productsLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch {
            try {
                val client = repository.getProducts()
                _productsLiveData.postValue(ScreenState.Success(client.result))
            } catch (e : Exception) {
                _productsLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }
    }


}