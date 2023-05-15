package com.example.easyshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.easyshop.databinding.ItemProductBinding
import com.example.easyshop.domain.Product

class ProductAdapter: ListAdapter<Product, ProductViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            name.text =getItem(position).name
        }
    }
}