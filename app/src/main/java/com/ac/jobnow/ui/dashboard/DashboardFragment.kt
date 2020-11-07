package com.ac.jobnow.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.jobnow.databinding.DashboardFragmentBinding
import com.ac.jobnow.repository.model.jobModels.Job
import com.ac.jobnow.ui.dashboard.adapter.DashboardApplicantAdapter
import com.ac.jobnow.utils.OnJobItemClick

class DashboardFragment : Fragment(), OnJobItemClick {

    private var dashboardBinding: DashboardFragmentBinding? = null
    private val binding get() = dashboardBinding!!
    private val dashboardViewModel: DashboardViewModel by activityViewModels()
    private var jobs: ArrayList<Job> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardBinding = DashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRecyclerView()
        updateRecyclerViewData()
    }

    private fun updateRecyclerViewData() {
        if (!handleUserType()) {
            //APPLICANT ADAPTER
            dashboardViewModel.apply {
                getJobs()
                jobsResult.observe(viewLifecycleOwner, {
                    jobs.clear()
                    jobs.addAll(it)
                    binding.rvDashboard.adapter?.notifyDataSetChanged()
                })
            }
        } else {
            //RECRUITER ADAPTER
        }
    }

    private fun loadRecyclerView() {
        if (!handleUserType()) {
            //APPLICANT ADAPTER
            binding.rvDashboard.apply {
                adapter =
                    DashboardApplicantAdapter(
                        jobs,
                        this@DashboardFragment
                    )
                layoutManager = LinearLayoutManager(context)
            }
        } else {
            //RECRUITER ADAPTER
        }
    }

    private fun handleUserType(): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.getBoolean("isRecruiter", false)
            .let { return if (it == true) it else false }
    }

    override fun onJobClick(position: Int) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToJobDetailsFragment(jobs[position])
        findNavController().navigate(action)
    }
}
