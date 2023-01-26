package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentFavoriteDetailsBinding
import bfa.blair.shopme.model.room.Favorite
import bfa.blair.shopme.ui.adapters.ProductImagesAdapter

class FavoriteDetailsFragment : Fragment() {

    private var binding : FragmentFavoriteDetailsBinding? = null

    private var favoriteDetails : Favorite? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteDetailsBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : FavoriteDetailsFragmentArgs by navArgs()

        favoriteDetails = args.favoriteDetails

        args.let {

            val productImages = it.favoriteDetails.images

            val adapter = ProductImagesAdapter(productImages, this@FavoriteDetailsFragment)
            binding!!.productImagesRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding!!.productImagesRV.adapter = adapter

            binding!!.productTitle.text = it.favoriteDetails.title
            binding!!.productBrand.text = "Brand: ${it.favoriteDetails.brand}"
            binding!!.productDescription.text = it.favoriteDetails.description
            binding!!.productRating.text = "Rating: ${it.favoriteDetails.rating}/5"
            binding!!.productPrice.text = "$ ${it.favoriteDetails.price}"
            binding!!.productDiscount.text = "${it.favoriteDetails.discountPercentage}% off"

        }

        binding!!.backHomeScreen.setOnClickListener {
            findNavController().popBackStack()
        }

    }


}