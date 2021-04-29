package com.akokash.ccount.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FoodDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food: Food)

    @Query("DELETE FROM food_table")
    fun deleteAll()

    @Delete
    fun deleteFood(food: Food)


    @Query("SELECT * FROM food_table LIMIT 1")
    fun getAnyFood(): Array<Food>

    @Query("SELECT * FROM food_table ORDER BY food_id DESC")
    fun getAllFood(): LiveData<List<Food>>
}