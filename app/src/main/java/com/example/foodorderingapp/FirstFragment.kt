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
import com.example.foodorderingapp.ui.HomeAdapter
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

        homeViewModel.homeLiveData.observe(viewLifecycleOwner, {
            it.let {
                homeAdapter.submitList(it)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
