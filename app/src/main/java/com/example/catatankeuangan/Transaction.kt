package com.example.catatankeuangan

data class Transaction(
    val description: String,
    val amount: Double,
    val type: Type
) {
    enum class Type { INCOME, EXPENSE }
}
