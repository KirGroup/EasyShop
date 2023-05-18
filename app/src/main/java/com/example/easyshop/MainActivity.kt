package com.example.easyshop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.adapter.ProductAdapter
import com.example.easyshop.databinding.ActivityMainBinding
import com.example.easyshop.domain.Product
import com.example.easyshop.domain.Stub

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()


    }

    private fun setAdapter() {
        productAdapter = ProductAdapter {
            val product = Product(it.id, it.name, !it.checked)
            Stub.stubList[it.id.toInt()] = product
            val newList = ArrayList<Product>(Stub.stubList)
            productAdapter.submitList(newList)
        }
        recyclerView = binding.listProduct
        recyclerView.adapter = productAdapter
        productAdapter.submitList(Stub.stubList)
    }
}