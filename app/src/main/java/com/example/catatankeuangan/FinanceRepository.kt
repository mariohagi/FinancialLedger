package com.example.catatankeuangan

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import androidx.room.Room
import android.content.Context

class FinanceRepository private constructor(internal val db: AppDatabase) {
    companion object {
        @Volatile private var INSTANCE: FinanceRepository? = null
        fun getInstance(context: Context): FinanceRepository {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "financial_ledger.db"
                )
                // Fix #2: The old onCreate callback opened a SECOND Room instance and
                // inserted a wallet unconditionally — root cause of duplicate wallets.
                // All seeding is now in the single guarded coroutine below.
                .fallbackToDestructiveMigration()
                .build()

                val repo = FinanceRepository(db)

                CoroutineScope(Dispatchers.IO).launch {
                    val walletDao = db.walletDao()

                    // Fix #2: De-duplicate "My Wallet" on existing installs.
                    val existingWallets = walletDao.getAll()
                    val myWallets = existingWallets.filter { it.name == "My Wallet" }.sortedBy { it.id }
                    if (myWallets.size > 1) {
                        myWallets.drop(1).forEach { walletDao.delete(it) }
                    }

                    // Seed exactly ONE default wallet on fresh installs.
                    if (walletDao.getAll().isEmpty()) {
                        walletDao.insert(
                            Wallet(0, "My Wallet", WalletType.CASH, 0.0, 0xFF006D5B.toInt(), "account_balance_wallet")
                        )
                    }

                    // Seed default categories on fresh installs.
                    val categoryDao = db.categoryDao()
                    if (categoryDao.getAll().isEmpty()) {
                        listOf(
                            Category("Food & Dining",   android.R.drawable.ic_menu_mapmode),
                            Category("Transport",       android.R.drawable.ic_menu_directions),
                            Category("Shopping",        android.R.drawable.ic_menu_add),
                            Category("Entertainment",   android.R.drawable.ic_menu_gallery),
                            Category("Salary",          android.R.drawable.ic_menu_myplaces),
                            Category("Freelance",       android.R.drawable.ic_menu_edit),
                            Category("Investment",      android.R.drawable.ic_menu_today),
                            Category("General",         android.R.drawable.ic_menu_info_details)
                        ).forEach { categoryDao.insert(it) }
                    }
                }

                INSTANCE = repo
                repo
            }
        }
    }

    val allTransactions: Flow<List<Transaction>> = db.transactionDao().getAllFlow()
    val incomeTransactions: Flow<List<Transaction>> = db.transactionDao().getByTypeFlow(Transaction.Type.INCOME)
    val expenseTransactions: Flow<List<Transaction>> = db.transactionDao().getByTypeFlow(Transaction.Type.EXPENSE)
    val allCategories: Flow<List<Category>> = db.categoryDao().getAllFlow()
    val allWallets: Flow<List<Wallet>> = db.walletDao().getAllFlow()

    val walletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("My Wallet")
    val bankBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("Bank")
    val stockWalletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("Stock Wallet")
    fun getWalletBalanceFlow(walletName: String): Flow<Double> =
        db.transactionDao().getBalanceByWalletSourceFlow(walletName)

    suspend fun insert(tx: Transaction) = db.transactionDao().insert(tx)
    suspend fun update(tx: Transaction) = db.transactionDao().update(tx)
    suspend fun delete(tx: Transaction) = db.transactionDao().delete(tx)

    suspend fun getDescriptionSuggestions(query: String): List<String> =
        db.transactionDao().getDescriptionSuggestions(query)

    fun getTransactionsByYearMonth(year: Int, month: Int) =
        db.transactionDao().getByYearMonthFlow(year, month)

    suspend fun insertWallet(wallet: Wallet) = db.walletDao().insert(wallet)
    suspend fun updateWallet(wallet: Wallet, oldName: String? = null) {
        db.walletDao().update(wallet)
        if (oldName != null && oldName != wallet.name) {
            db.transactionDao().updateWalletSource(oldName, wallet.name)
        }
    }
    suspend fun deleteWallet(wallet: Wallet) = db.walletDao().delete(wallet)

    suspend fun getAllTransactionsList(): List<Transaction> = db.transactionDao().getAll()
    suspend fun getAllCategoriesList(): List<Category> = db.categoryDao().getAll()
    suspend fun getAllWalletsList(): List<Wallet> = db.walletDao().getAll()
    suspend fun insertTransactions(txList: List<Transaction>) =
        txList.forEach { db.transactionDao().insert(it) }
}
