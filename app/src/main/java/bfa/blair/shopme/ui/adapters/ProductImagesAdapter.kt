package bfa.blair.shopme.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import bfa.blair.shopme.databinding.ProductImagesLayoutBinding
import coil.load

class ProductImagesAdapter(
    private val productImages: List<String>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<ProductImagesAdapter.ProductImagesViewHolder>() {

    class ProductImagesViewHolder(private val binding: ProductImagesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindProductImages(productImages: String) {
            binding.productImagesView.load(productImages) {
                crossfade(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImagesViewHolder {
        return ProductImagesViewHolder(
            ProductImagesLayoutBinding.inflate(
                LayoutInflater.from(fragment.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductImagesViewHolder, position: Int) {
        val productImagesPosition = productImages[position]
        holder.bindProductImages(productImagesPosition)
    }

    override fun getItemCount(): Int {
        return productImages.size
    }

}