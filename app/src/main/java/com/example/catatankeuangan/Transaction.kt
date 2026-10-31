package com.example.catatankeuangan

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val description: String,
    val amount: Double,
    val type: Type,
    val category: String = "General",
    val date: Date = Date()
) {
    enum class Type { INCOME, EXPENSE }
}
