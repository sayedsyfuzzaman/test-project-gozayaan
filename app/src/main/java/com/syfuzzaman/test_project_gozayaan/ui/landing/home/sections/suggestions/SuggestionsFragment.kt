package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentSuggestionsBinding
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback

class SuggestionsFragment : Fragment(), BaseListItemCallback<TripInfo> {
    private var _binding: FragmentSuggestionsBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: TripInfoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuggestionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = TripInfoListAdapter(this)
        with(binding?.rvTrips) {
            this?.adapter = mAdapter
        }

        val trips = listOf(
            TripInfo(1, "Mountain Safari", "India", true),
            TripInfo(2, "Beach Side", "Hawai"),
            TripInfo(2, "Safari Park", "Thailand"),
            TripInfo(2, "Tiger Hill", "Nepal"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
        )
        mAdapter.addAll(trips.toList())
        mAdapter.notifyDataSetChanged()


        binding?.seeAllButton?.setOnClickListener {
            requireActivity().findNavController(R.id.mainHostFragmentContainer)
                .navigate(R.id.action_landingFragment_to_seeDetailsFragment)
        }
    }

    override fun onItemClicked(item: TripInfo) {
        super.onItemClicked(item)
        requireActivity().findNavController(R.id.mainHostFragmentContainer)
            .navigate(R.id.action_landingFragment_to_pageDetailsFragment)
    }
}