package com.example.foodorderingapp.model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on categoryLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialCategoryList = inventoryCategoryList(resources)
    private val categoryLiveData = MutableLiveData(initialCategoryList)

    fun getInventoryCategoryList(): LiveData<List<InventoryCategory>> {
        return categoryLiveData
    }

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