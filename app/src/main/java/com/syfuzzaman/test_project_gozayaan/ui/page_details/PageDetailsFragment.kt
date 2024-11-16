package com.syfuzzaman.test_project_gozayaan.ui.page_details

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentPageDetailsBinding

class PageDetailsFragment : Fragment() {
    private var _binding: FragmentPageDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            requireActivity().handleSystemBarsVisibility(true)
        }
        binding?.topAppBar?.backButton?.setOnClickListener{
            requireActivity().findNavController(R.id.mainHostFragmentContainer).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().handleSystemBarsVisibility(false)
        _binding = null
    }


    private fun Activity.handleSystemBarsVisibility(enable: Boolean) {
        if (enable) {
            WindowCompat.setDecorFitsSystemWindows(
                this.window,
                false
            )
            WindowInsetsControllerCompat(
                this.window,
                this.window.decorView
            ).let { controller ->
                controller.hide(
                    WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.displayCutout()
                )
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                this.window.attributes = this.window.attributes.apply {
                    layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pageDetailsFragmentRoot)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(0, 0, 0, systemBars.bottom)
                insets
            }

        } else {
            WindowCompat.setDecorFitsSystemWindows(
                this.window,
                true
            )
            WindowInsetsControllerCompat(
                this.window,
                this.window.decorView
            ).let { controller ->
                controller.show(
                    WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.displayCutout()
                )
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                this.window.attributes = this.window.attributes.apply {
                    layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
                }
            }
        }
    }
}