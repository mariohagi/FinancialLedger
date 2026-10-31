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
    val walletSource: WalletSource = WalletSource.WALLET,
    val date: Date = Date()
) {
    enum class Type { INCOME, EXPENSE }
}

enum class WalletSource(val displayName: String, val icon: String) {
    WALLET("Wallet", "account_balance_wallet"),
    BANK("Bank", "account_balance"),
    STOCK_WALLET("Stock Wallet", "trending_up")
}
