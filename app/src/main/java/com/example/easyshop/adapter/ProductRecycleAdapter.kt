package com.example.easyshop.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.databinding.ItemProductBinding
import com.example.easyshop.domain.Product

class ProductRecycleAdapter(
    private val onItemTouchCallBack: ((Product) -> Unit),
    private val onLongItemTouchCallBack: (Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    private lateinit var productList: List<Product>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            name.text = productList[position].name
            if (productList[position].checked) {
                name.paintFlags = name.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else name.paintFlags = name.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

            name.setOnClickListener {
                onItemTouchCallBack.invoke(productList[position])
            }

            name.setOnLongClickListener {
                onLongItemTouchCallBack.invoke(productList[position])
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(productList: List<Product>){
        this.productList = productList
        notifyDataSetChanged()
    }
}