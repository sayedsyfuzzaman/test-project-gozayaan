package com.syfuzzaman.test_project_gozayaan.ui.see_all

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.data.model.Resource
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentSeeAllBinding
import com.syfuzzaman.test_project_gozayaan.ui.utils.observe
import com.syfuzzaman.test_project_gozayaan.ui.MainViewModel
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.utils.navigateTo
import kotlin.getValue

class SeeAllFragment : Fragment(), BaseListItemCallback<HotelInfo> {
    private var _binding: FragmentSeeAllBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: HotelInfoListGridAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.topAppBar?.topbarTitle?.text = "Recommended"

        binding?.topAppBar?.backButton?.setOnClickListener{
            requireActivity().findNavController(R.id.mainHostFragmentContainer).popBackStack()
        }

        mAdapter = HotelInfoListGridAdapter(this)
        with(binding?.rvTrips) {
            this?.adapter = mAdapter
        }
        val data = viewModel.getHotelList()
        if (!data.isNullOrEmpty()){
            data[0].isBookmarked = true
            mAdapter.removeAll()
            mAdapter.addAll(data)
            mAdapter.notifyDataSetChanged()
        }
    }
    override fun onItemClicked(item: HotelInfo) {
        super.onItemClicked(item)

        val bundle = Bundle().apply {
            putParcelable("hotelInfo", item)
        }

        requireActivity().findNavController(R.id.mainHostFragmentContainer)
            .navigateTo(R.id.pageDetailsFragment, bundle)
    }
}