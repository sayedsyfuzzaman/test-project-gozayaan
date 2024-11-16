package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentCategoriesBinding
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback

class CategoriesFragment : Fragment(), BaseListItemCallback<Category> {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: CategoryListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = CategoryListAdapter(this)
        with(binding?.rvCategories) {
            this?.adapter = mAdapter
        }

        val categories = listOf(
            Category(1, "Flights", R.drawable.ic_flights),
            Category(2, "Hotels", R.drawable.ic_hotels),
            Category(3, "Visa", R.drawable.ic_visa),
            Category(4, "Buses", R.drawable.ic_buses),
            Category(5, "Flights", R.drawable.ic_flights)
        )
        mAdapter.addAll(categories.toList())
        mAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(item: Category) {
        super.onItemClicked(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}