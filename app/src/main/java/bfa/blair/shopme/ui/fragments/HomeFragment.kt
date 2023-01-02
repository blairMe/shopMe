package bfa.blair.shopme.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentHomeBinding
import bfa.blair.shopme.model.network.Product
import bfa.blair.shopme.ui.activities.ScreenState
import bfa.blair.shopme.ui.adapters.ProductsAdapter
import bfa.blair.shopme.viewmodel.ProductsViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val viewModel : ProductsViewModel by lazy {
        ViewModelProvider(requireActivity())[ProductsViewModel::class.java]
    }

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

        viewModel.productsLiveData.observe(requireActivity()) { state ->
            processProductsResponse(state)
        }
    }

    private fun processProductsResponse(state : ScreenState<List<Product>?>) {
        when(state) {
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.progressBar.visibility = View.GONE
                if (state.data != null) {
                    val adapter = ProductsAdapter(state.data, this@HomeFragment)
                    binding.productsRV.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.productsRV.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.VISIBLE
                Snackbar.make(binding.progressBar.rootView, state.message!!, Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    fun productDetails(product: Product) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetails(product))
    }

}