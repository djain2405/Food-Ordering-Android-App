package com.example.foodorderingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.ItemCategoryListBinding
import com.example.foodorderingapp.model.InventoryCategory
import java.lang.IllegalArgumentException

class HomeAdapter : ListAdapter<HomeAdapter.HomeItem, HomeAdapter.HomeViewHolder>(HomeDiffCallback) {
    object HomeDiffCallback: DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
            oldItem == newItem

    }

    sealed class HomeItem(val type: Int, val id: Int) {
        data class InventoryCategoryItem(
            val categoryList: List<InventoryCategory>
        ) : HomeItem(INVENTORY_CATEGORY_LIST,INVENTORY_CATEGORY_LIST)
    }

    sealed class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(homeItem: HomeItem)

        class InventoryCategoryViewHolder(private val binding: ItemCategoryListBinding) : HomeViewHolder(binding.root) {
            private val categoryAdapter by lazy {
                InventoryCategoryAdapter()
            }
            override fun bind(homeItem: HomeItem) {
                binding.categoryRecyclerview.apply {
                    adapter = categoryAdapter
                }
                categoryAdapter.submitList((homeItem as HomeItem.InventoryCategoryItem).categoryList)
            }

        }
    }

    companion object {
        private const val INVENTORY_CATEGORY_LIST = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        return when(viewType) {
            INVENTORY_CATEGORY_LIST -> HomeViewHolder.InventoryCategoryViewHolder(
                ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ) else -> throw IllegalArgumentException("Unsupported viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}