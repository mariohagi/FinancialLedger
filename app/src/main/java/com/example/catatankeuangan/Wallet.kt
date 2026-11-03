package com.example.catatankeuangan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class Wallet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: WalletType = WalletType.CASH,
    val balance: Double = 0.0,
    val color: Int = 0xFF006D5B.toInt(),
    val icon: String = "account_balance_wallet"
)

enum class WalletType(val displayName: String) {
    CASH("Cash"),
    BANK("Bank"),
    CREDIT_CARD("Credit Card"),
    E_WALLET("E-Wallet"),
    INVESTMENT("Investment"),
    SAVINGS("Savings"),
    CRYPTO("Crypto"),
    OTHER("Other")
}