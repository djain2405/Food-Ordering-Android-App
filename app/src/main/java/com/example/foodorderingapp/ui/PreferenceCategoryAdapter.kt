package com.example.foodorderingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.ItemPreferenceCategoryBinding
import com.example.foodorderingapp.model.PreferenceCategory

class PreferenceCategoryAdapter :
    ListAdapter<PreferenceCategory, PreferenceCategoryAdapter.PreferenceCategoryViewHolder>(
        PreferenceDiffCallback
    ) {
    class PreferenceCategoryViewHolder(private val binding: ItemPreferenceCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PreferenceCategory) {
            binding.preferenceButton.text = item.title
            if (item.leftIcon != null && item.rightIcon != null) {
                binding.preferenceButton.setLeftRightIcon(item.leftIcon, item.rightIcon)
            } else if (item.leftIcon != null) {
                binding.preferenceButton.setLeftIcon(item.leftIcon)
            } else if (item.rightIcon != null) {
                binding.preferenceButton.setRightIcon(item.rightIcon)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreferenceCategoryViewHolder {
        return PreferenceCategoryViewHolder(
            ItemPreferenceCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PreferenceCategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

object PreferenceDiffCallback : DiffUtil.ItemCallback<PreferenceCategory>() {
    override fun areItemsTheSame(
        oldItem: PreferenceCategory,
        newItem: PreferenceCategory
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PreferenceCategory,
        newItem: PreferenceCategory
    ): Boolean = oldItem == newItem

}