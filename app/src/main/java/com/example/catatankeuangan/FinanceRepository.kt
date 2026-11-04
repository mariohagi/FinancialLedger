package com.example.catatankeuangan

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import androidx.room.Room
import androidx.room.RoomDatabase
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
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed default categories and wallets
                        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                            val dbInstance = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java,
                                "financial_ledger.db"
                            ).build()
                            val categoryDao = dbInstance.categoryDao()
                            val walletDao = dbInstance.walletDao()
                            val defaultCategories = listOf(
                                Category("Food & Dining", android.R.drawable.ic_menu_mapmode),
                                Category("Transport", android.R.drawable.ic_menu_directions),
                                Category("Shopping", android.R.drawable.ic_menu_add),
                                Category("Entertainment", android.R.drawable.ic_menu_gallery),
                                Category("Salary", android.R.drawable.ic_menu_myplaces),
                                Category("Freelance", android.R.drawable.ic_menu_edit),
                                Category("Investment", android.R.drawable.ic_menu_today),
                                Category("General", android.R.drawable.ic_menu_info_details)
                            )
                            defaultCategories.forEach { categoryDao.insert(it) }
                            
                            // Exactly ONE default wallet
                            walletDao.insert(
                                Wallet(0, "My Wallet", WalletType.CASH, 0.0, 0xFF006D5B.toInt(), "account_balance_wallet")
                            )
                        }
                    }
                }).fallbackToDestructiveMigration().build()
                val repo = FinanceRepository(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val walletDao = db.walletDao()
                    if (walletDao.getAll().isEmpty()) {
                        walletDao.insert(
                            Wallet(0, "My Wallet", WalletType.CASH, 0.0, 0xFF006D5B.toInt(), "account_balance_wallet")
                        )
                    }
                    val categoryDao = db.categoryDao()
                    if (categoryDao.getAll().isEmpty()) {
                        val defaultCategories = listOf(
                            Category("Food & Dining", android.R.drawable.ic_menu_mapmode),
                            Category("Transport", android.R.drawable.ic_menu_directions),
                            Category("Shopping", android.R.drawable.ic_menu_add),
                            Category("Entertainment", android.R.drawable.ic_menu_gallery),
                            Category("Salary", android.R.drawable.ic_menu_myplaces),
                            Category("Freelance", android.R.drawable.ic_menu_edit),
                            Category("Investment", android.R.drawable.ic_menu_today),
                            Category("General", android.R.drawable.ic_menu_info_details)
                        )
                        defaultCategories.forEach { categoryDao.insert(it) }
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
    
    // Wallet source balance flows
    val walletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("My Wallet")
    val bankBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("Bank")
    val stockWalletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow("Stock Wallet")
    fun getWalletBalanceFlow(walletName: String): Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow(walletName)

    suspend fun insert(tx: Transaction) = db.transactionDao().insert(tx)
    suspend fun update(tx: Transaction) = db.transactionDao().update(tx)
    suspend fun delete(tx: Transaction) = db.transactionDao().delete(tx)
    
    suspend fun insertWallet(wallet: Wallet) = db.walletDao().insert(wallet)
    suspend fun updateWallet(wallet: Wallet, oldName: String? = null) {
        db.walletDao().update(wallet)
        if (oldName != null && oldName != wallet.name) {
            db.transactionDao().updateWalletSource(oldName, wallet.name)
        }
    }
    suspend fun deleteWallet(wallet: Wallet) = db.walletDao().delete(wallet)
}
