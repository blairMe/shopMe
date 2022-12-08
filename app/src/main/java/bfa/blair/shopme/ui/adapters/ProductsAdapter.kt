package bfa.blair.shopme.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.shopme.databinding.ProductItemLayoutBinding
import bfa.blair.shopme.model.network.Product
import coil.Coil
import coil.load
import coil.transform.RoundedCornersTransformation

class ProductsAdapter(private val productList: List<Product>, private val fragment : Fragment) :
        RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

        class ProductViewHolder(private val binding: ProductItemLayoutBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    fun bindProducts(product: Product) {
                        val productName = binding.productName
                        val image = binding.productImage
                        val category = binding.productCategory
                        val price = binding.productPrice

                        productName.text = product.title
                        category.text = product.category
                        price.text = "$ ${product.price}"
                        image.load(product.thumbnail) {
                            crossfade(true)
                            transformations(RoundedCornersTransformation(20f))
                        }
                    }
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
        holder.bindProducts(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}