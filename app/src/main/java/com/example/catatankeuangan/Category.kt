package com.example.catatankeuangan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val name: String,
    val iconRes: Int = android.R.drawable.ic_menu_info_details,
    val limit: Double? = null // optional monthly budget limit
)
