package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentCartBinding
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.ui.adapters.CartAdapter
import bfa.blair.shopme.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var binding : FragmentCartBinding? = null

    val cartViewModel: CartViewModel by viewModels()

    var itemPrice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.cartList.observe(viewLifecycleOwner) { response ->
            cartProductResponse(response)
        }

        binding!!.cartBtn.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToCheckoutFragment(itemPrice.toString()))
        }

    }

    private fun cartProductResponse(response : List<Cart>) {
        if(response.isNotEmpty()) {
            val adapter = CartAdapter(this@CartFragment)
            binding!!.rvCart.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding!!.rvCart.adapter = adapter

            adapter.cartList(response)



            response.forEach {priceAmnt ->
                itemPrice += priceAmnt.price.toInt()
                binding!!.payableAmount.text = itemPrice.toString()
            }

        } else {
            Log.d("Cart", "You have nothing")
        }

    }

    fun productDetails(cart: Cart) {
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToCartDetailsFragment(cart))
    }

}