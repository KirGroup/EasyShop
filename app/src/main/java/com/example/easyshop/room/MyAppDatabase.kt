package com.example.easyshop.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.easyshop.domain.Product

@Database(entities = [Product::class], version = 1)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}