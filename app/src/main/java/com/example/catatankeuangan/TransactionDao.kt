package com.example.catatankeuangan

import androidx.room.*

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tx: Transaction)

    @Query("SELECT * FROM transaction")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM transaction WHERE type = :type")
    fun getByType(type: Transaction.Type): List<Transaction>
}
