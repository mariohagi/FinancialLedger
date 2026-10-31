package com.example.catatankeuangan

import androidx.room.*

@Dao
interface RecurringDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entry: RecurringEntry)

    @Query("SELECT * FROM recurring_entries")
    fun getAll(): List<RecurringEntry>
}
