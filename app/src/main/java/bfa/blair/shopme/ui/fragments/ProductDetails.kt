package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentProductDetailsBinding
import bfa.blair.shopme.network.Product
import bfa.blair.shopme.network.ProductList

class ProductDetails : Fragment() {

    lateinit var binding : FragmentProductDetailsBinding

    private var productDetails : ProductList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : ProductDetailsArgs by navArgs()

        productDetails = args.theProductDetails

         args.let {

            val theTitle = it.theProductDetails.title
            Log.d("The title", "$theTitle")
            Toast.makeText(this.requireContext(), "$theTitle", Toast.LENGTH_SHORT).show()
        }

    }

}