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
}

