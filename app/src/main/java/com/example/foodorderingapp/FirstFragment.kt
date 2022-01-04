package com.example.foodorderingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.FragmentFirstBinding
import com.example.foodorderingapp.databinding.ItemInventoryCategoryBinding
import com.example.foodorderingapp.model.InventoryCategory
import com.example.foodorderingapp.ui.HomeViewModel
import com.example.foodorderingapp.ui.HomeViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(requireContext())
    }

    private val categoryAdapter by lazy {
        InventoryCategoryAdapter()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecyclerview.apply {
            adapter = categoryAdapter
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        homeViewModel.categoryLiveData.observe(viewLifecycleOwner, {
            it.let {
                categoryAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class InventoryCategoryAdapter :
    ListAdapter<InventoryCategory, InventoryCategoryAdapter.InventoryCategoryViewHolder>(
        CategoryDiffCallback
    ) {
    class InventoryCategoryViewHolder(private val binding: ItemInventoryCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InventoryCategory) {
            binding.categoryName.text = item.title
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
