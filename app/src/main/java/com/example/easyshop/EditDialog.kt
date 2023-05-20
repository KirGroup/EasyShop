package com.example.easyshop

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.easyshop.domain.Product

class EditDialog(
    context: Context,
    private val product: Product,
    private val onEdit: (Product) -> Unit,
    private val onDelete: (Product) -> Unit
) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Edit")
        setMessage("Enter new value:")
        val input = EditText(context)
        input.setText(product.name)
        setView(input)
        setButton(BUTTON_POSITIVE, "Ok") { dialog, which ->
            val newValue = input.text.toString()
            val product1 = Product(product.id, newValue, product.checked)
            onEdit.invoke(product1)
        }

        setButton(BUTTON_NEGATIVE, "Cancel") { dialog, which ->
            dialog.cancel()
        }

        setButton(BUTTON_NEUTRAL, "Delete") { dialog, which ->
            onDelete.invoke(product)
        }

        super.onCreate(savedInstanceState)
    }
}