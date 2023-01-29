package bfa.blair.shopme.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.repository.ProductsRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: ProductsRoomRepository) : ViewModel() {

    val cartList : LiveData<List<Cart>> = repository.getCart().asLiveData()

    fun insertProduct(cart: Cart) = viewModelScope.launch { repository.insertProduct(cart) }
    fun updateCart(cart: Cart) = viewModelScope.launch { repository.updateCart(cart) }

    fun deleteCart() = viewModelScope.launch { repository.deleteAllCartItems() }

}