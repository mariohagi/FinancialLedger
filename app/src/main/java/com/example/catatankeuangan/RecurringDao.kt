package com.example.catatankeuangan

import androidx.room.*

@Dao
interface RecurringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: RecurringEntry)

    @Query("SELECT * FROM recurringentry")
    fun getAll(): List<RecurringEntry>
}
