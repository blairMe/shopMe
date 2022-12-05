package bfa.blair.shopme.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.shopme.databinding.ProductItemLayoutBinding
import bfa.blair.shopme.model.network.Product

class ProductsAdapter(private val productList: List<Product>, private val fragment : Fragment) :
        RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

        class ProductViewHolder(private val binding: ProductItemLayoutBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    val title = binding.productName.toString()
                    val image = binding.productImage.toString()
                    val category = binding.productCategory.toString()
                    val price = binding.productPrice.toString()
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(fragment.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}