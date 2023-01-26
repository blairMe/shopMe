package bfa.blair.shopme.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import bfa.blair.shopme.databinding.CartItemLayoutBinding
import bfa.blair.shopme.model.room.Cart
import bfa.blair.shopme.ui.fragments.CartFragment
import coil.load
import coil.transform.RoundedCornersTransformation

class CartAdapter(val fragment : Fragment) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cart : List<Cart> = listOf()

    class CartViewHolder(private val binding : CartItemLayoutBinding) : ViewHolder(binding.root) {
        fun bindCart(cart: Cart) {
            val cartThumbnail = binding.cartImage
            val cartName = binding.cartName
            val cartBrand = binding.cartBrand
            val cartPrice = binding.cartBrand

            cartThumbnail.load(cart.thumbnail) {
                crossfade(true)
                transformations(RoundedCornersTransformation(10f))
            }
            cartName.text = cart.title
            cartBrand.text = cart.brand
            cartPrice.text = cart.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemLayoutBinding.inflate(
                LayoutInflater.from(fragment.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartPosition = cart[position]
        holder.bindCart(cartPosition)
        holder.itemView.setOnClickListener {
            if (fragment is CartFragment) {
                fragment.productDetails(cartPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return cart.size
    }

    fun cartList(list : List<Cart>) {
        cart = list
        notifyDataSetChanged()
    }


}