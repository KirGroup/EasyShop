package com.example.easyshop

import android.content.Context
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
        binding.addButton.setOnClickListener {
            createAddDialog(this)
        }
        setAdapter()
    }

    private fun setAdapter() {
        productAdapter = ProductAdapter({
            val product = Product(it.id, it.name, !it.checked)
            val touchList = ArrayList<Product>(Stub.stubList)
            touchList[touchList.indexOf(it)] = product
            productAdapter.submitList(touchList)
            Stub.stubList[Stub.stubList.indexOf(it)] = product
        }, {
            createEditDialog(this, it)
        }
        )
        recyclerView = binding.listProduct
        recyclerView.adapter = productAdapter
        val initList = ArrayList<Product>(Stub.stubList)
        productAdapter.submitList(initList)
    }

    private fun createEditDialog(context: Context, product: Product) {
        EditDialog(context, product, {
            Stub.stubList[Stub.stubList.indexOf(product)] = it
            val editList = ArrayList<Product>(Stub.stubList)
            editList[editList.indexOf(it)] = it
            productAdapter.submitList(editList)
        }, {
            val deleteList = ArrayList<Product>(Stub.stubList)
            deleteList.remove(it)
            productAdapter.submitList(deleteList)
            Stub.stubList.remove(it)
        }).show()
    }

    private fun createAddDialog(context: Context) {
        AddDialog(context) {
            val addList = ArrayList<Product>(Stub.stubList)
            val addItem = Product(((Stub.stubList.last()).id) + 1, it, false)
            addList.add(addItem)
            productAdapter.submitList(addList)
            Stub.stubList.add(addItem)
        }.show()
    }
}