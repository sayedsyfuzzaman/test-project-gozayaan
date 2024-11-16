package com.syfuzzaman.test_project_gozayaan.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.syfuzzaman.test_project_gozayaan.R
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.hostFragmentContainer) as NavHostFragment
        binding?.bottomNavigationView?.apply {
            setupWithNavController(navHostFragment.navController)
            // disable reloading fragment when clicking again on the same tab
            setOnNavigationItemReselectedListener {}
        }
    }
}