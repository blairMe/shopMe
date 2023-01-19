package bfa.blair.shopme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
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
    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())
    val cartList = _cartList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCart().distinctUntilChanged()
                .collect { theCartList ->
                    if(theCartList.isNullOrEmpty()) {
                        Log.d("Cart", "Empty Cart")
                    } else {
                        _cartList.value = theCartList
                    }
                }
        }
    }

    fun insertProduct(cart: Cart) = viewModelScope.launch { repository.insertProduct(cart) }

}