package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import bfa.blair.shopme.databinding.FragmentProductDetailsBinding
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.ui.adapters.ProductImagesAdapter
import bfa.blair.shopme.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetails : Fragment() {

    var binding : FragmentProductDetailsBinding? = null

    private var productDetails : ProductList? = null

    private val viewModel : CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : ProductDetailsArgs by navArgs()

        productDetails = args.theProductDetails

         args.let {

             val productImages = it.theProductDetails.images

             val adapter = ProductImagesAdapter(productImages, this@ProductDetails)
             binding!!.productImagesRV.layoutManager =
                 LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
             binding!!.productImagesRV.adapter = adapter

             binding!!.productTitle.text = it.theProductDetails.title
             binding!!.productBrand.text = "Brand: ${it.theProductDetails.brand}"
             binding!!.productDescription.text = it.theProductDetails.description
             binding!!.productRating.text = "Rating: ${it.theProductDetails.rating}/5"
             binding!!.productPrice.text = "$ ${it.theProductDetails.price}"
             binding!!.productDiscount.text = "${it.theProductDetails.discountPercentage}% off"

        }

        binding!!.backHomeScreen.setOnClickListener {
            findNavController().popBackStack()
        }

        binding!!.cartBtn.setOnClickListener {

                val title = args.theProductDetails.title
                val brand = args.theProductDetails.brand
                val description = args.theProductDetails.description
                val rating = args.theProductDetails.rating.toString()
                val price = args.theProductDetails.price.toString()
                val discountPercentage = args.theProductDetails.discountPercentage.toString()
                val thumbnail = args.theProductDetails.thumbnail
                val images = args.theProductDetails.images

                val cart = Cart(
                id = null,
                title,
                brand,
                description,
                price,
                discountPercentage,
                rating,
                thumbnail,
                images )

                viewModel.insertProduct(cart)

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}