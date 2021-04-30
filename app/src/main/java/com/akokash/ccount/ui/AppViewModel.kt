package com.akokash.ccount.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.akokash.ccount.database.Food
import com.akokash.ccount.database.FoodRepository

class AppViewModel(app: Application) : AndroidViewModel(app) {

    var toDelete: Int = -1
    var calories_budget: Int = 2000
    var calories_soFar: Int = 0

    init {
        FoodRepository.initialize(app)
    }

    private val foodRepository = FoodRepository.get()
    val food = foodRepository.getAllFoods()

    fun insert(food: Food) {
        foodRepository.insert(food)
    }

    fun deleteFood(food: Food) {
        foodRepository.deleteFood(food)
    }
}