package com.syfuzzaman.test_project_gozayaan.ui.landing.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentHomeBinding
import com.syfuzzaman.test_project_gozayaan.ui.MainViewModel
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.see_all.HotelInfoListGridAdapter
import com.syfuzzaman.test_project_gozayaan.ui.utils.navigateTo
import com.syfuzzaman.test_project_gozayaan.ui.utils.observe

class HomeFragment : Fragment(){
    private companion object {
        private const val FADE_DURATION = 300L
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val query: String get() = binding.searchView.inputField.text.trim().toString()
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mAdapter: HotelInfoListGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        setupSearch()
    }

    private fun setupAdapter() {
        mAdapter = HotelInfoListGridAdapter(object : BaseListItemCallback<HotelInfo> {
            override fun onItemClicked(item: HotelInfo) {
                super.onItemClicked(item)
                val bundle = Bundle().apply {
                    putParcelable("hotelInfo", item)
                }

                requireActivity().findNavController(R.id.mainHostFragmentContainer)
                    .navigateTo(R.id.pageDetailsFragment, bundle)
            }
        })
        with(binding.rvSearchResult) {
            this.adapter = mAdapter
        }
    }

    private fun setupObservers() {
        observe(viewModel.searchResult){ filteredList->
            if (!filteredList.isNullOrEmpty()) {
                mAdapter.removeAll()
                mAdapter.addAll(filteredList)
                mAdapter.notifyDataSetChanged()
                showSearchView()
            } else {
                hideSearchView()
            }
        }

        observe(viewModel.isSearchResultEmpty){
            if (it) {
                Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
                hideSearchView()
            }
        }
    }

    private fun setupSearch() {
        updateClearButtonVisibility(query)

        binding.searchView.inputField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                viewModel.search(query)
                true
            } else {
                false
            }
        }

        binding.searchView.inputField.doAfterTextChanged { newText ->
            updateClearButtonVisibility(newText)
        }

        binding.searchView.clearInputButton.setOnClickListener {
            binding.searchView.inputField.setText("")
            viewModel.clearSearchItems()
            hideSearchView()
        }
    }

    private fun showSearchView() {
        binding.searchResultTitle.visibility = View.VISIBLE
        binding.rvSearchResult.visibility = View.VISIBLE
        binding.categoriesFragment.visibility = View.GONE
        binding.suggestionFragment.visibility = View.GONE
    }

    private fun hideSearchView() {
        mAdapter.removeAll()
        binding.searchResultTitle.visibility = View.GONE
        binding.rvSearchResult.visibility = View.GONE
        binding.categoriesFragment.visibility = View.VISIBLE
        binding.suggestionFragment.visibility = View.VISIBLE
    }

    private fun updateClearButtonVisibility(text: CharSequence?) {
        val isClearButtonVisible = !text.isNullOrEmpty()
        if (isClearButtonVisible && !binding.searchView.clearInputButton.isVisible) {
            TransitionManager.beginDelayedTransition(binding.root, Fade().setDuration(FADE_DURATION))
        }
        binding.searchView.clearInputButton.isVisible = isClearButtonVisible
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchView.inputField.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}