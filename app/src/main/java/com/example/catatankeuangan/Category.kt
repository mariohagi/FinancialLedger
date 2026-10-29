package com.example.catatankeuangan

data class Category(
    val name: String,
    val iconRes: Int = android.R.drawable.ic_menu_info_details,
    val limit: Double? = null // optional monthly budget limit
)
