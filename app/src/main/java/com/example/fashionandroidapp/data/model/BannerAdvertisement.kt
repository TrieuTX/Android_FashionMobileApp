package com.example.fashionandroidapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banner_table")
data class BannerAdvertisement(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUrl: String,
)