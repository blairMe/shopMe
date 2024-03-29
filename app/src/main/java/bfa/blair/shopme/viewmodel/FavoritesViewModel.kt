package bfa.blair.shopme.viewmodel

import android.util.Log
import androidx.lifecycle.*
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.repository.ProductsRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: ProductsRoomRepository)
    : ViewModel() {

    val favoriteList : LiveData<List<Favorite>> = repository.getFavorite.asLiveData()

    fun insertFavorite(favorite : Favorite) = viewModelScope.launch { repository.insertFavorite(favorite) }

}