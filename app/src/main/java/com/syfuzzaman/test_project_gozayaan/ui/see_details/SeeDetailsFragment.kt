package com.syfuzzaman.test_project_gozayaan.ui.see_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentSeeDetailsBinding
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions.TripInfo

class SeeDetailsFragment : Fragment(), BaseListItemCallback<TripInfo> {
    private var _binding: FragmentSeeDetailsBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: TripInfoListGridAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSeeDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.topAppBar?.topbarTitle?.text = "Recommended"

        binding?.topAppBar?.backButton?.setOnClickListener{
            requireActivity().findNavController(R.id.mainHostFragmentContainer).popBackStack()
        }

        mAdapter = TripInfoListGridAdapter(this)
        with(binding?.rvTrips) {
            this?.adapter = mAdapter
        }

        val trips = listOf(
            TripInfo(1, "Mountain Safari", "India", true),
            TripInfo(2, "Beach Side", "Hawai"),
            TripInfo(2, "Safari Park", "Thailand"),
            TripInfo(2, "Tiger Hill", "Nepal"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
            TripInfo(2, "Thai Zoo House", "Thailand"),
        )
        mAdapter.addAll(trips.toList())
        mAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(item: TripInfo) {
        super.onItemClicked(item)
        requireActivity().findNavController(R.id.mainHostFragmentContainer)
            .navigate(R.id.action_seeDetailsFragment_to_pageDetailsFragment)
    }
}