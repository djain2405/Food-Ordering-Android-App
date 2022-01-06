package com.example.foodorderingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapp.databinding.FragmentFirstBinding
import com.example.foodorderingapp.databinding.ItemCategoryListBinding
import com.example.foodorderingapp.databinding.ItemInventoryCategoryBinding
import com.example.foodorderingapp.model.InventoryCategory
import com.example.foodorderingapp.ui.HomeViewModel
import com.example.foodorderingapp.ui.HomeViewModelFactory
import java.lang.IllegalArgumentException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val homeViewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(requireContext())
    }

    private val homeAdapter by lazy {
        HomeAdapter()
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
            adapter = homeAdapter
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        homeViewModel.categoryLiveData.observe(viewLifecycleOwner, {
            it.let {
                val homeList = mutableListOf<HomeAdapter.HomeItem>()
                homeList.add(HomeAdapter.HomeItem.InventoryCategoryItem(it))
                homeAdapter.submitList(homeList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

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
