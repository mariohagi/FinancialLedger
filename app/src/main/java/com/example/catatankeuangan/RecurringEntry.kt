package com.example.catatankeuangan

data class RecurringEntry(
    val description: String,
    val amount: Double,
    val type: Transaction.Type,
    val dayOfMonth: Int // 1‑31, when to auto‑add each month
)
