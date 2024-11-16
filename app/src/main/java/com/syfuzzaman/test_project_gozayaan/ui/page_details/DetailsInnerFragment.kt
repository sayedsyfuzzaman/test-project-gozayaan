package com.syfuzzaman.test_project_gozayaan.ui.page_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentDetailsInnerBinding
import com.syfuzzaman.test_project_gozayaan.ui.common.BaseListItemCallback
import com.syfuzzaman.test_project_gozayaan.ui.page_details.banner.DetailImage
import com.syfuzzaman.test_project_gozayaan.ui.page_details.banner.DetailImageListAdapter


class DetailsInnerFragment : Fragment(), BaseListItemCallback<DetailImage> {
    private var _binding: FragmentDetailsInnerBinding? = null
    private val binding get() = _binding
    private lateinit var mAdapter: DetailImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsInnerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = DetailImageListAdapter(this)

        binding?.let {
            it.featuredViewpager.adapter = mAdapter
            TabLayoutMediator(
                it.tabIndicator,
                it.featuredViewpager,
                true
            ) { _, _ -> }.attach()
        }

        val imageList = listOf(
            DetailImage("https://png.pngtree.com/background/20221201/original/pngtree-cinema-film-festival-movie-poster-background-picture-image_1973552.jpg"),
            DetailImage("https://png.pngtree.com/background/20221201/original/pngtree-cinema-film-festival-movie-poster-background-picture-image_1973552.jpg"),
            DetailImage("https://png.pngtree.com/background/20221201/original/pngtree-cinema-film-festival-movie-poster-background-picture-image_1973552.jpg"),
            DetailImage("https://png.pngtree.com/background/20221201/original/pngtree-cinema-film-festival-movie-poster-background-picture-image_1973552.jpg"),
        )

        mAdapter.addAll(imageList)
        mAdapter.notifyDataSetChanged()
        binding?.featuredViewpager?.visibility = View.VISIBLE
    }
}