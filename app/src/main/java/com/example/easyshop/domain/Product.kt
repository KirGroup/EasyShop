package com.example.easyshop.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var checked: Boolean
)