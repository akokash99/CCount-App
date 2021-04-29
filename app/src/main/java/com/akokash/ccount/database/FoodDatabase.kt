package com.akokash.ccount.database

import android.content.ClipData
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Food::class], version = 1, exportSchema = true)

abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}