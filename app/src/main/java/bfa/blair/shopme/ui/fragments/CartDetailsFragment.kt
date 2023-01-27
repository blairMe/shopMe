package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentCartDetailsBinding
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.ui.adapters.ProductImagesAdapter

class CartDetailsFragment : Fragment() {

    private var binding : FragmentCartDetailsBinding? = null

    private var cartDetails : Cart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartDetailsBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : CartDetailsFragmentArgs by navArgs()

        cartDetails = args.cartDetails

        args.let {
            val productImages = it.cartDetails.images

            val adapter = ProductImagesAdapter(productImages, this@CartDetailsFragment)
            binding!!.productImagesRV.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding!!.productImagesRV.adapter = adapter

            binding!!.productTitle.text = it.cartDetails.title
            binding!!.productBrand.text = "Brand: ${it.cartDetails.brand}"
            binding!!.productDescription.text = it.cartDetails.description
            binding!!.productRating.text = "Rating: ${it.cartDetails.rating}/5"
            binding!!.productPrice.text = "$ ${it.cartDetails.price}"
            binding!!.productDiscount.text = "${it.cartDetails.discountPercentage}% off"
        }

        binding!!.backHomeScreen.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}