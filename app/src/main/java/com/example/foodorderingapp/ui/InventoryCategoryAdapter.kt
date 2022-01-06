package com.example.foodorderingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.ItemInventoryCategoryBinding
import com.example.foodorderingapp.model.InventoryCategory

class InventoryCategoryAdapter :
    ListAdapter<InventoryCategory, InventoryCategoryAdapter.InventoryCategoryViewHolder>(
        CategoryDiffCallback
    ) {
    class InventoryCategoryViewHolder(private val binding: ItemInventoryCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InventoryCategory) {
            binding.categoryName.text = item.title
            binding.categoryImageview.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, item.categoryImage))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryCategoryViewHolder {
        return InventoryCategoryViewHolder(
            ItemInventoryCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: InventoryCategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

object CategoryDiffCallback : DiffUtil.ItemCallback<InventoryCategory>() {
    override fun areItemsTheSame(oldItem: InventoryCategory, newItem: InventoryCategory): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: InventoryCategory,
        newItem: InventoryCategory
    ): Boolean = oldItem == newItem

}