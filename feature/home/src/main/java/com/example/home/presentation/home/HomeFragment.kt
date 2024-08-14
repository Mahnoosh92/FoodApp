package com.example.home.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.domain.model.Category
import com.example.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryAdapter = CategoryAdapter() {}
        setupUi(categoryAdapter = categoryAdapter)
        setupListeners(categoryAdapter = categoryAdapter)

        viewModel.onEvent(GetFoodsEvent.GetFoods)
    }

    private fun setupUi(categoryAdapter: CategoryAdapter) {
        setUpCategoryRecyclerView(categoryAdapter = categoryAdapter)
    }

    private fun setUpCategoryRecyclerView(categoryAdapter: CategoryAdapter) {
        binding.categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupListeners(categoryAdapter: CategoryAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    val (loading, recipes, categories, failure) = state
                    updateLoading(loading)
                    updateCategories(categories, categoryAdapter = categoryAdapter)
                }
            }
        }
    }

    private fun updateCategories(categories: List<Category>, categoryAdapter: CategoryAdapter) {
        categoryAdapter.submitList(categories)
        Log.i("reza", "updateCategories: " + categories)
    }

    private fun updateLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}