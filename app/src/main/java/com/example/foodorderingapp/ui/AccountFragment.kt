package com.example.foodorderingapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.foodorderingapp.databinding.FragmentAccountDialogBinding
import com.example.foodorderingapp.model.AccountItem

class AccountFragment: DialogFragment() {
    private lateinit var binding: FragmentAccountDialogBinding
    private val accountAdapter by lazy {
        AccountAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountDialogBinding.inflate(inflater, container, false)
        binding.accountRecyclerview.apply {
            adapter = accountAdapter
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
        }
        return binding.root
    }

    fun setAccountList(accountItemList: List<AccountItem>) {
        accountAdapter.submitList(accountItemList)
    }
}