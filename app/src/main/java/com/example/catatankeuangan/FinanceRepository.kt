package com.example.catatankeuangan

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
                            
                            val defaultWallets = listOf(
                                                            Wallet(0, "Cash Wallet", WalletType.CASH, 0.0, 0xFF006D5B.toInt(), "account_balance_wallet"),
                                                            Wallet(0, "Bank Account", WalletType.BANK, 0.0, 0xFF4A6FA5.toInt(), "account_balance"),
                                                            Wallet(0, "Credit Card", WalletType.CREDIT_CARD, 0.0, 0xFFC41A1A.toInt(), "credit_card"),
                                                            Wallet(0, "E-Wallet", WalletType.E_WALLET, 0.0, 0xFFE87C00.toInt(), "phone_android")
                                                        )
                            defaultWallets.forEach { walletDao.insert(it) }
                        }
                    }
                }).fallbackToDestructiveMigration().build()
                val repo = FinanceRepository(db)
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
    val walletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow(WalletSource.WALLET)
    val bankBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow(WalletSource.BANK)
    val stockWalletBalance: Flow<Double> = db.transactionDao().getBalanceByWalletSourceFlow(WalletSource.STOCK_WALLET)

    suspend fun insert(tx: Transaction) = db.transactionDao().insert(tx)
    suspend fun update(tx: Transaction) = db.transactionDao().update(tx)
    suspend fun delete(tx: Transaction) = db.transactionDao().delete(tx)
    
    suspend fun insertWallet(wallet: Wallet) = db.walletDao().insert(wallet)
    suspend fun updateWallet(wallet: Wallet) = db.walletDao().update(wallet)
    suspend fun deleteWallet(wallet: Wallet) = db.walletDao().delete(wallet)
}
