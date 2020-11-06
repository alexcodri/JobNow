package com.ac.jobnow.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ac.jobnow.databinding.DashboardFragmentBinding

class DashboardFragment : Fragment() {

    private var dashboardBinding: DashboardFragmentBinding? = null
    private val binding get() = dashboardBinding!!
    private val dashboardViewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardBinding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
