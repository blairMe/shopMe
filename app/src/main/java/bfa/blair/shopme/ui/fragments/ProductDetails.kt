package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import bfa.blair.shopme.databinding.FragmentProductDetailsBinding
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.ui.adapters.ProductImagesAdapter

class ProductDetails : Fragment() {

    var binding : FragmentProductDetailsBinding? = null

    private var productDetails : ProductList? = null

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

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}