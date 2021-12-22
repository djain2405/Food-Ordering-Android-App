package com.example.foodorderingapp.model

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData

/* Handles operations on categoryLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialCategoryList = inventoryCategoryList(resources)
    private val categoryLiveData = MutableLiveData(initialCategoryList)

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}