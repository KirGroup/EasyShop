package com.example.easyshop.adapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.easyshop.databinding.ItemProductBinding
import com.example.easyshop.domain.Product

class ProductAdapter(
    private val onItemTouchCallBack: ((Product) -> Unit),
    private val onLongItemTouchCallBack: (Product) -> Unit
) : ListAdapter<Product, ProductViewHolder>(DiffCallBackLog()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            name.text = getItem(position).name
            if (getItem(position).checked) {
                name.paintFlags = name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else name.paintFlags = name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

            name.setOnClickListener {
                onItemTouchCallBack.invoke(getItem(holder.adapterPosition))
            }

            name.setOnLongClickListener {
                onLongItemTouchCallBack.invoke(getItem(holder.adapterPosition))
                true
            }
        }
    }
}