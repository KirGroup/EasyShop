package com.example.easyshop

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshop.adapter.ProductRecycleAdapter
import com.example.easyshop.databinding.ActivityMainBinding
import com.example.easyshop.domain.Product
import com.example.easyshop.room.ProductDao

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    //    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRecycleAdapter: ProductRecycleAdapter
    private lateinit var database: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = ProductDomain.init(this).productDao()
        setContentView(binding.root)
        binding.addButton.setOnClickListener {
            createAddDialog(this)
        }
//        setAdapter()
        setRecycleAdapter()
    }

//    private fun setAdapter() {
//        productAdapter = ProductAdapter({ listProduct ->
//            val touchList = ArrayList<Product>(database.getAllProduct())
//            val product = touchList.find { it == listProduct }
//            Log.d("setAdapter1", "$product")
//            Log.d("setAdapter1", "dataBaseList: ${database.getAllProduct().hashCode()} adapterList: ${productAdapter.currentList.hashCode()}")
//            touchList[touchList.indexOf(product)] = listProduct.apply { checked = !checked }
//            productAdapter.submitList(touchList)
//            database.updateProduct(listProduct)
//        }, {
//            createEditDialog(this, it)
//        }
//        )
//        recyclerView = binding.listProduct
//        recyclerView.adapter = productAdapter
//        val initList = ArrayList<Product>(database.getAllProduct())
//        productAdapter.submitList(initList)
//    }

    private fun setRecycleAdapter() {
        productRecycleAdapter = ProductRecycleAdapter({ listProduct ->
            val touchList = ArrayList<Product>(database.getAllProduct())
            val product = touchList.find { it == listProduct }
            touchList[touchList.indexOf(product)] = listProduct.apply { checked = !checked }
            productRecycleAdapter.setList(touchList)
            database.updateProduct(listProduct)
        }, {
            createEditDialog(this, it)
        }
        )
        recyclerView = binding.listProduct
        recyclerView.adapter = productRecycleAdapter
        val initList = ArrayList<Product>(database.getAllProduct())
        productRecycleAdapter.setList(initList)
    }

    private fun createEditDialog(context: Context, product: Product) {
        EditDialog(context, product, {
            database.updateProduct(it)
            val editList = ArrayList<Product>(database.getAllProduct())
            editList[editList.indexOf(it)] = it
            productRecycleAdapter.setList(editList)
        }, {
            val deleteList = ArrayList<Product>(database.getAllProduct())
            deleteList.remove(it)
            productRecycleAdapter.setList(deleteList)
            database.deleteProduct(it)
        }).show()
    }

    private fun createAddDialog(context: Context) {
        AddDialog(context) {
            val existingProduct = database.getProductByName(it)
            if (existingProduct == null) {
                val addList = ArrayList<Product>(database.getAllProduct())
                val addItem = Product(0, it, false)
                addList.add(addItem)
                productRecycleAdapter.setList(addList)
                database.insertProduct(addItem)
            } else {
                Toast.makeText(context, "Product already exists!", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }
}