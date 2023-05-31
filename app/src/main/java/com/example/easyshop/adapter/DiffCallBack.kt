package com.example.easyshop.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.easyshop.domain.Product

class DiffCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class DiffCallBackLog : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        Log.d("DiffCallBackLog", "areItemsTheSame: $oldItem $newItem")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        Log.d("DiffCallBackLog", "areContentsTheSame: $oldItem $newItem")
        return oldItem == newItem
    }
}