package bfa.blair.shopme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.repository.ProductsRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: ProductsRoomRepository)
    : ViewModel() {
    private val _favoriteList = MutableStateFlow<List<Favorite>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()


    fun insertFavorite(favorite : Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }

}