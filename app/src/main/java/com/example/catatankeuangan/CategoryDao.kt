package com.example.catatankeuangan

import androidx.room.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: Category)

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>
}
