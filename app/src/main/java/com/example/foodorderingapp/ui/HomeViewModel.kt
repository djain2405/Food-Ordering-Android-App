package com.example.foodorderingapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodorderingapp.model.DataSource

class HomeViewModel(val dataSource: DataSource) : ViewModel() {

    val categoryLiveData = dataSource.getInventoryCategoryList()
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}