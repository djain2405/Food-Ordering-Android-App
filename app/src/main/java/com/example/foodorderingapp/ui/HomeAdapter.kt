package com.example.foodorderingapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.ItemCategoryListBinding
import com.example.foodorderingapp.databinding.ItemPreferenceListBinding
import com.example.foodorderingapp.model.InventoryCategory
import com.example.foodorderingapp.model.PreferenceCategory
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
        data class PreferenceCategoryItem(
            val preferenceList: List<PreferenceCategory>
        ) : HomeItem(PREFERENCE_CATEGORY_LIST, PREFERENCE_CATEGORY_LIST)
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

        class PreferenceCategoryViewHolder(private val binding: ItemPreferenceListBinding) : HomeViewHolder(binding.root) {
            private val preferenceAdapter by lazy {
                PreferenceCategoryAdapter()
            }
            override fun bind(homeItem: HomeItem) {
                binding.preferenceRecyclerview.apply {
                    adapter = preferenceAdapter
                }
                preferenceAdapter.submitList((homeItem as HomeItem.PreferenceCategoryItem).preferenceList)
            }

        }
    }

    companion object {
        private const val INVENTORY_CATEGORY_LIST = 0
        private const val PREFERENCE_CATEGORY_LIST = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when(viewType) {
            INVENTORY_CATEGORY_LIST -> HomeViewHolder.InventoryCategoryViewHolder(
                ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            PREFERENCE_CATEGORY_LIST -> HomeViewHolder.PreferenceCategoryViewHolder(
                ItemPreferenceListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Unsupported viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int = currentList[position].type

}