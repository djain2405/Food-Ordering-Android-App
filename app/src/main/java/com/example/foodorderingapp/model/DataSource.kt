package com.example.foodorderingapp.model

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData

/* Handles operations on categoryLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialCategoryList = inventoryCategoryList(resources)
    private val categoryLiveData = MutableLiveData(initialCategoryList)
}