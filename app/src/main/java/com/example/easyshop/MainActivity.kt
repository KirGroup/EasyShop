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
import com.example.easyshop.room.MyAppDatabase
import com.example.easyshop.room.ProductDao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var database: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = ProductDomain.init(this).productDao()
        setContentView(binding.root)
        binding.addButton.setOnClickListener {
            createAddDialog(this)
        }
        setAdapter()
    }

    private fun setAdapter() {
        productAdapter = ProductAdapter({ listProduct ->
//            val product = Product(listProduct.id, listProduct.name, !listProduct.checked)
            val touchList = ArrayList<Product>(database.getAllProduct())
            val product = touchList.find { it == listProduct }
            Log.d("setAdapter1", "$product")
            Log.d("setAdapter1", "dataBaseList: ${database.getAllProduct().hashCode()} adapterList: ${productAdapter.currentList.hashCode()}")
            touchList[touchList.indexOf(product)] = listProduct.apply { checked = !checked }
            productAdapter.submitList(touchList)
            database.updateProduct(listProduct)
        }, {
            createEditDialog(this, it)
        }
        )
        recyclerView = binding.listProduct
        recyclerView.adapter = productAdapter
        val initList = ArrayList<Product>(database.getAllProduct())
        productAdapter.submitList(initList)
    }

    private fun createEditDialog(context: Context, product: Product) {
        Log.d("createEditDialog", "$product")
        EditDialog(context, product, {
            database.updateProduct(it)
            val editList = ArrayList<Product>(database.getAllProduct())
            editList[editList.indexOf(it)] = it
            productAdapter.submitList(editList)
        }, {
            val deleteList = ArrayList<Product>(database.getAllProduct())
            deleteList.remove(it)
            productAdapter.submitList(deleteList)
            database.deleteProduct(it)
        }).show()
    }

    private fun createAddDialog(context: Context) {
        AddDialog(context) {
            val addList = ArrayList<Product>(database.getAllProduct())
            val addItem = Product(0, it, false)
            addList.add(addItem)
            productAdapter.submitList(addList)
            database.insertProduct(addItem)
        }.show()
    }
}