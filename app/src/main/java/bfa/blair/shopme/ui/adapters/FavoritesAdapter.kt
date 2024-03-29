package bfa.blair.shopme.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.shopme.databinding.FavoriteItemLayoutBinding
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.ui.fragments.FavoriteFragment
import coil.load
import coil.transform.RoundedCornersTransformation

class FavoritesAdapter(val fragment : Fragment)
    : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private var favorites : List<Favorite> = listOf()

    class FavoriteViewHolder(private val binding : FavoriteItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bindFavorites(favorite: Favorite) {
            val productName = binding.productName
            val productImage = binding.productImage
            val productBrand = binding.productBrand
            val productPrice = binding.productPrice

            productName.text = favorite.title
            productImage.load(favorite.thumbnail) {
                crossfade(true)
                transformations(RoundedCornersTransformation(20f))
            }
            productBrand.text = favorite.brand
            productPrice.text = "$ ${favorite.price}"

            binding.addToCart.setOnClickListener {

            }

            binding.deleteItem.setOnClickListener {
                Log.d("Add to cart", "Del")
                Log.d("Add to cart", "Del22")
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteItemLayoutBinding.inflate(
                LayoutInflater.from(fragment.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoritePosition = favorites[position]
        holder.bindFavorites(favoritePosition)
        holder.itemView.setOnClickListener {
            if(fragment is FavoriteFragment) {
                fragment.favoriteDetails(favoritePosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    fun favoriteList(list: List<Favorite>) {
        favorites = list
        notifyDataSetChanged()
    }

}