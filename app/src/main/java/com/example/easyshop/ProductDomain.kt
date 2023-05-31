package com.example.easyshop

import android.content.Context
import androidx.room.Room
import com.example.easyshop.room.MyAppDatabase

object ProductDomain {
    fun init(context: Context) : MyAppDatabase {
        return Room.databaseBuilder(context, MyAppDatabase::class.java, "my-database").allowMainThreadQueries().build()
    }
}