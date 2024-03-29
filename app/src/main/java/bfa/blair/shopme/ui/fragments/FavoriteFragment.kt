package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentFavoriteBinding
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.ui.adapters.FavoritesAdapter
import bfa.blair.shopme.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var binding : FragmentFavoriteBinding? = null
    private val favoritesViewModel : FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel.favoriteList.observe(viewLifecycleOwner) { response ->
            favoriteProductResponse(response)
        }
    }

    private fun favoriteProductResponse(response: List<Favorite>?) {
        if (response!!.isNotEmpty()) {
            val adapter = FavoritesAdapter(this@FavoriteFragment)
            binding!!.rvFavorites.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding!!.rvFavorites.adapter = adapter

            adapter.favoriteList(response)
        } else {
            Log.d("Favorites", "You have nothing")
        }
    }

    fun favoriteDetails(favorite: Favorite) {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailsFragment(favorite))
    }
    

}