package com.akokash.ccount.model

import android.content.Context

import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.akokash.ccount.R
import kotlin.random.Random

class FactsGenerator(context: Context?) {

    init {


    }


    var fact = ""
    val strings = arrayOf(

        context?.getString(R.string.fact1),
        context?.getString(R.string.fact2),
        context?.getString(R.string.fact3),
        context?.getString(R.string.fact4),
        context?.getString(R.string.fact5),
        context?.getString(R.string.fact6),
        context?.getString(R.string.fact7),
        context?.getString(R.string.fact8),
        context?.getString(R.string.fact9)
    )




    fun fact_generate(): String?{

        val random_val = Random.nextInt(0, 9)

        fact= strings[random_val].toString()
        return fact
    }
}