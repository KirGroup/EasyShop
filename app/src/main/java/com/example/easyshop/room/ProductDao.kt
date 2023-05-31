package com.example.easyshop.room

import androidx.room.*
import com.example.easyshop.domain.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProduct(): List<Product>

    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)
}