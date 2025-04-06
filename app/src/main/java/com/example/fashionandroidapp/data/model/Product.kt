package com.example.fashionandroidapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey() val id: Int = 0,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val category: Category
)

