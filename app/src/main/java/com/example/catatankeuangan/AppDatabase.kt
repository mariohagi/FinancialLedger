package com.example.catatankeuangan

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class, RecurringEntry::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun recurringDao(): RecurringDao
    abstract fun categoryDao(): CategoryDao
}
