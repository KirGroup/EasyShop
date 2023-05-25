package com.example.easyshop

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class AddDialog(
    context: Context,
    private val onAdd: (String) -> Unit
) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle("Add") //fixme ввести верные названия во всех диалгах
        setMessage("Enter new value:")
        val input = EditText(context)
        input.setText("Add item")
        setView(input)

        setButton(BUTTON_POSITIVE, "Ok") { dialog, which ->
            onAdd.invoke(input.text.toString())
        }

        setButton(BUTTON_NEGATIVE, "Cancel") { dialog, which ->
            dialog.cancel()
        }

        super.onCreate(savedInstanceState)
    }
}