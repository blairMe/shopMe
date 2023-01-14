package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentHomeBinding
import bfa.blair.shopme.model.network.Product
import bfa.blair.shopme.model.network.ProductList
import bfa.blair.shopme.ui.adapters.ProductsAdapter
import bfa.blair.shopme.utils.DataorException
import bfa.blair.shopme.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val viewModel : ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return(binding.root)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Change status bar color for this fragment and the rest
        requireActivity().window.statusBarColor = this.resources.getColor(R.color.white)

        viewModel.productsLiveData.observe(viewLifecycleOwner) { state ->
            processProductsResponse(state)
        }
    }

    private fun processProductsResponse(state : DataorException<Product, Boolean, Exception>) {


        if(state.loading == true) {
            binding.progressBar.visibility = View.VISIBLE
        } else if (state.data != null) {
            binding.progressBar.visibility = View.GONE

                val adapter = ProductsAdapter(state.data, this@HomeFragment)
                binding.productsRV.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                binding.productsRV.adapter = adapter
        } else {
            // binding.progressBar.visibility = View.VISIBLE
            //Snackbar.make(binding.progressBar.rootView, state.message!!, Snackbar.LENGTH_SHORT).show()
        }

    }

    fun productDetails(product: ProductList) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetails(product))
    }

}