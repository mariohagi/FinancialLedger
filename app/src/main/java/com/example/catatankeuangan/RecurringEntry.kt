package com.example.catatankeuangan


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catatankeuangan.Transaction

@Entity(tableName = "recurring_entries")
data class RecurringEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val amount: Double,
    val type: Transaction.Type,
    val dayOfMonth: Int // 1‑31, when to auto‑add each month
)
