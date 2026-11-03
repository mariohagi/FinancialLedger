package com.example.catatankeuangan

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wallet: Wallet)

    @Update
    suspend fun update(wallet: Wallet)

    @Delete
    suspend fun delete(wallet: Wallet)

    @Query("SELECT * FROM wallets ORDER BY name ASC")
    fun getAll(): List<Wallet>

    @Query("SELECT * FROM wallets ORDER BY name ASC")
    fun getAllFlow(): Flow<List<Wallet>>

    @Query("SELECT * FROM wallets WHERE id = :id")
    fun getById(id: Int): Wallet?

    @Query("DELETE FROM wallets")
    suspend fun deleteAll()
}