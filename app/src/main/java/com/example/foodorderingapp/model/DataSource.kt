package com.example.foodorderingapp.model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodorderingapp.ui.HomeAdapter

/* Handles operations on categoryLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialCategoryList = inventoryCategoryList(resources)
    private val initialPreferenceList = preferenceCategoryList(resources)

    fun getHomeList() : LiveData<List<HomeAdapter.HomeItem>> {
        val homeList = mutableListOf<HomeAdapter.HomeItem>()
        initialCategoryList.let {
            homeList.add(HomeAdapter.HomeItem.InventoryCategoryItem(it))
        }
        initialPreferenceList.let {
            homeList.add(HomeAdapter.HomeItem.PreferenceCategoryItem(it))
        }
        return MutableLiveData(homeList)
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