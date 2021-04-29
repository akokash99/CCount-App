package com.akokash.ccount.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "food_table")
data class Food(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    var id: Long = 0L,


    @ColumnInfo(name = "food_name")
    var fanme: String = "",


    @ColumnInfo(name = "food_calories")
    var lname: String = "",

    @ColumnInfo(name = "food_fat")
    var food_fat: Int = 0,

    @ColumnInfo(name = "food_protein")
    var food_protein: Int = 0,

    @ColumnInfo(name = "food_carbs")
    var food_carbs: Int = 0

)
