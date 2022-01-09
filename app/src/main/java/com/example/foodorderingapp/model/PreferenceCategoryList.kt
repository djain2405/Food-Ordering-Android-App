package com.example.foodorderingapp.model

import android.content.res.Resources
import com.example.foodorderingapp.R

/* Returns initial list of Preference Category. */
fun preferenceCategoryList(resources: Resources): List<PreferenceCategory> {
    return listOf(
        PreferenceCategory(
            id = 1,
            title = resources.getString(R.string.dashpass),
            leftIcon = R.drawable.cart
        ),
        PreferenceCategory(
            id = 2,
            title = resources.getString(R.string.pickup),
        ),
        PreferenceCategory(
            id = 3,
            title = resources.getString(R.string.rating),
            leftIcon = R.drawable.star,
            rightIcon = R.drawable.chevron_down
        ),
        PreferenceCategory(
            id = 4,
            title = resources.getString(R.string.price),
            rightIcon = R.drawable.chevron_down
        ),
        PreferenceCategory(
            id = 5,
            title = resources.getString(R.string.under_30_mins),
        ),
    )
}