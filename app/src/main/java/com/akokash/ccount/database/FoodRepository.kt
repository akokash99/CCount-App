package com.akokash.ccount.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

class FoodRepository private constructor(context: Context) {

    private val database: FoodDatabase = Room.databaseBuilder(
        context.applicationContext,
        FoodDatabase::class.java,
        "word_database"
    )
        .build()


    private val foodDao = database.foodDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllFoods(): LiveData<List<Food>> = foodDao.getAllFood()

    fun insert(food: Food) {
        executor.execute {
            foodDao.insert(food)
        }
    }

    fun deleteFood(food: Food) {
        executor.execute {
            foodDao.deleteFood(food)
        }
    }

    companion object {
        private var INSTANCE: FoodRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = FoodRepository(context)
            }
        }

        fun get(): FoodRepository {
            return INSTANCE
                ?: throw java.lang.IllegalStateException("FoodRepository must be initialized.")
        }
    }
}
