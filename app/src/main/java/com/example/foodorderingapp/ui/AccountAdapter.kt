package com.example.foodorderingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.ItemAccountBinding
import com.example.foodorderingapp.model.AccountItem

class AccountAdapter : ListAdapter<AccountItem, AccountAdapter.AccountViewHolder>(AccountDiffUtilCallback) {
    class AccountViewHolder(private val binding: ItemAccountBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(accountItem: AccountItem) {
                    binding.title.text = accountItem.title
                    binding.subtitle.text = accountItem.subtitle
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

object AccountDiffUtilCallback: DiffUtil.ItemCallback<AccountItem>() {
    override fun areItemsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean
        = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: AccountItem, newItem: AccountItem): Boolean
        = oldItem == newItem

}