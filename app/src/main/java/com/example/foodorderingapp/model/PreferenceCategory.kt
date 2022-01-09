package com.example.foodorderingapp.model

import androidx.annotation.DrawableRes

data class PreferenceCategory(val id: Long, val title: String, @DrawableRes val leftIcon: Int? = null, @DrawableRes val rightIcon: Int? = null)