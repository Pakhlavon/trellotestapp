package com.app.kattabozor.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kattabozor.databinding.ProductsItemBinding
import com.app.kattabozor.model.Offers
import com.app.kattabozor.model.OffersResponce
import com.app.kattabozor.utils.loadImageFromGlide
import javax.inject.Inject

class ProductAdapter @Inject constructor() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProductsItemBinding) :
        RecyclerView.ViewHolder(binding.root)
    private val diffUtil = object : DiffUtil.ItemCallback<Offers>() {
        override fun areItemsTheSame(oldItem: Offers, newItem: Offers): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offers, newItem: Offers): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = differ.currentList[position]
        println(product)
        holder.binding.apply {
            productImage.loadImageFromGlide(product.image!!.url)
            productName.text = product.name
            productDesc.text = "${product.attributes.get(0).value} / ${product.attributes.get(1).value}"
            productBrand.text = product.brand
            productMerchant.text = product.merchant
          }
        holder.itemView.setOnClickListener {
            setProductClickListener?.let {
                it(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var setProductClickListener : ((offers: Offers)->Unit)? =null

    fun onArticleClicked(listener:(Offers)->Unit){
        setProductClickListener =listener
    }
    fun refreshData(){
        notifyDataSetChanged()
    }
}