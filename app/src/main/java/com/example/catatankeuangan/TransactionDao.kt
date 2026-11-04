package com.example.catatankeuangan

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tx: Transaction)

    @Update
    fun update(tx: Transaction)

    @Delete
    fun delete(tx: Transaction)

    @Query("SELECT * FROM transactions")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions")
    fun getAllFlow(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE type = :type")
    fun getByType(type: Transaction.Type): List<Transaction>

    @Query("SELECT * FROM transactions WHERE type = :type")
    fun getByTypeFlow(type: Transaction.Type): Flow<List<Transaction>>

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE walletSource = :walletSource")
    fun getBalanceByWalletSource(walletSource: String): Double

    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE walletSource = :walletSource")
    fun getBalanceByWalletSourceFlow(walletSource: String): Flow<Double>

    @Query("UPDATE transactions SET walletSource = :newName WHERE walletSource = :oldName")
    fun updateWalletSource(oldName: String, newName: String)

    @Query("DELETE FROM transactions")
    fun deleteAll()

    /** Filter by year/month for monthly navigation (date stored as ms epoch). */
    @Query("""
        SELECT * FROM transactions
        WHERE CAST(strftime('%Y', date/1000, 'unixepoch') AS INTEGER) = :year
          AND CAST(strftime('%m', date/1000, 'unixepoch') AS INTEGER) = :month
        ORDER BY date DESC
    """)
    fun getByYearMonthFlow(year: Int, month: Int): Flow<List<Transaction>>

    /** Distinct descriptions for autocomplete (case-insensitive prefix match). */
    @Query("""
        SELECT DISTINCT description FROM transactions
        WHERE LOWER(description) LIKE '%' || LOWER(:query) || '%'
          AND description != ''
        ORDER BY description
        LIMIT 8
    """)
    suspend fun getDescriptionSuggestions(query: String): List<String>
}
