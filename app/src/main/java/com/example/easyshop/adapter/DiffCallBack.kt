package com.example.easyshop.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.easyshop.domain.Product

class DiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        Log.d("SAME", "areItemsTheSame: ${oldItem.id == newItem.id}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        Log.d("SAME", "areContentsTheSame: ${oldItem==newItem}")
        return oldItem==newItem
    }
}