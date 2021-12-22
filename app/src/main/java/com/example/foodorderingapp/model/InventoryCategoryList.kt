package com.example.foodorderingapp.model

import android.content.res.Resources
import com.example.foodorderingapp.R

/* Returns initial list of Inventory Category. */
fun inventoryCategoryList(resources: Resources): List<InventoryCategory> {
    return listOf(
        InventoryCategory(
            id = 1,
            title = resources.getString(R.string.convenience),
        ),
        InventoryCategory(
            id = 2,
            title = resources.getString(R.string.alcohol),
        ),
        InventoryCategory(
            id = 3,
            title = resources.getString(R.string.vegan),
        ),
        InventoryCategory(
            id = 4,
            title = resources.getString(R.string.healthy),
        ),
        InventoryCategory(
            id = 5,
            title = resources.getString(R.string.fast_food),
        ),
    )
}