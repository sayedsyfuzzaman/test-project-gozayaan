package com.syfuzzaman.test_project_gozayaan.ui.landing.home.sections.suggestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.data.api.HotelInfo
import com.syfuzzaman.test_project_gozayaan.data.model.Resource
import com.syfuzzaman.test_project_gozayaan.ui.utils.observe
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentSuggestionsBinding
import com.syfuzzaman.test_project_gozayaan.ui.MainViewModel
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.utils.navigateTo

class SuggestionsFragment : Fragment(), BaseListItemCallback<HotelInfo> {
    private var _binding: FragmentSuggestionsBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: HotelInfoListAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuggestionsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = HotelInfoListAdapter(this)
        with(binding?.rvTrips) {
            this?.adapter = mAdapter
        }

        binding?.seeAllButton?.setOnClickListener {
            requireActivity().findNavController(R.id.mainHostFragmentContainer).navigateTo(R.id.action_landingFragment_to_seeDetailsFragment)
        }

        observeHotelList()
        viewModel.callHotelListApi()
    }

    fun observeHotelList(){
        observe(viewModel.hotelList) {
            when(it){
                is Resource.Success ->{
                    it.data?.let { data->
                        data[0].isBookmarked = true
                        mAdapter.removeAll()
                        mAdapter.addAll(data.filter { it.isAvailable == true })
                        mAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), it.error.msg, Toast.LENGTH_SHORT).show()
                }
            }
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