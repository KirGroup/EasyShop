package com.example.easyshop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
        productAdapter = ProductAdapter({
            val product = Product(it.id, it.name, !it.checked)
            val touchList = ArrayList<Product>(Stub.stubList)
            touchList[it.id.toInt()] = product
            productAdapter.submitList(touchList)
        }, {
            createDialog(this, it)
        }
        )
        recyclerView = binding.listProduct
        recyclerView.adapter = productAdapter
        val initList = ArrayList<Product>(Stub.stubList)
        productAdapter.submitList(initList)
    }

    private fun createDialog(context: Context, product: Product) {
        EditDialog(context, product, {
            val editList = ArrayList<Product>(Stub.stubList)
            editList[it.id.toInt()] = it
            productAdapter.submitList(editList)
        }, {
            val deleteList = ArrayList<Product>(Stub.stubList)
            deleteList.removeAt(it.id.toInt())
            productAdapter.submitList(deleteList)
            Log.d("areItemsTheSame", "createDialog delete from list")
            Stub.stubList.removeAt(it.id.toInt())
        }).show()
    }
}