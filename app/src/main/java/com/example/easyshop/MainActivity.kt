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
            Stub.stubList[it.id.toInt()] = product
            val newList = ArrayList<Product>(Stub.stubList)
            productAdapter.submitList(newList)
        }, {
            createDialog(this, it)
        }
        )
        recyclerView = binding.listProduct
        recyclerView.adapter = productAdapter
        productAdapter.submitList(Stub.stubList)
    }

    private fun createDialog(context: Context, product: Product) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit")
        builder.setMessage("Enter new value:")
        val input = EditText(context)
        input.setText(product.name)
        builder.setView(input)
        builder.setPositiveButton("Ok") { dialog, which ->
            val newValue = input.text.toString()
            val product1 = Product(product.id, newValue, product.checked)
            Stub.stubList[product1.id.toInt()] = product1
            val newList = ArrayList<Product>(Stub.stubList)
            productAdapter.submitList(newList)
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            // Обработка нажатия кнопки "Cancel"
            dialog.cancel()
        }

        // Создание и отображение диалога
        val dialog = builder.create()
        dialog.show()
    }
}
