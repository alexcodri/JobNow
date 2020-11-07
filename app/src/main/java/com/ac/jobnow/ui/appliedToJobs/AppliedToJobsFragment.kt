package com.ac.jobnow.ui.appliedToJobs

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.jobnow.databinding.AppliedToJobsFragmentBinding
import com.ac.jobnow.repository.model.jobModels.Job
import com.ac.jobnow.ui.appliedToJobs.adapter.AppliedToJobsAdapter

class AppliedToJobsFragment : Fragment() {
    private var appliedToJobsFragmentBinding: AppliedToJobsFragmentBinding? = null
    private val binding get() = appliedToJobsFragmentBinding!!
    private val appliedToJobsViewModel: AppliedToJobsViewModel by activityViewModels()
    private lateinit var userId: String
    private val jobs: ArrayList<Job> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appliedToJobsFragmentBinding =
            AppliedToJobsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getUserId()
        initRecyclerViewData()
        loadRecyclerViewData()
    }

    private fun loadRecyclerViewData() {
        appliedToJobsViewModel.getJobsForUser(userId)

        binding.rvAppliedToJobs.apply {
            appliedToJobsViewModel.jobs.observe(viewLifecycleOwner, {
                jobs.clear()
                jobs.addAll(it.jobs)
                adapter?.notifyDataSetChanged()
            })
        }
    }

    private fun initRecyclerViewData() {
        binding.rvAppliedToJobs.apply {
            adapter = AppliedToJobsAdapter(jobs)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getUserId() {
        val sharedPreferences = activity?.getPreferences( Context.MODE_PRIVATE)
        userId =sharedPreferences?.getString("userId", "") ?: ""
    }

}