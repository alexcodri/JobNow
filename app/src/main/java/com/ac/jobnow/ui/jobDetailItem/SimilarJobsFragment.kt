package com.ac.jobnow.ui.jobDetailItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac.jobnow.databinding.FragmentSimilarJobsBinding
import com.ac.jobnow.repository.model.jobModels.Job
import com.ac.jobnow.ui.dashboard.DashboardFragmentDirections
import com.ac.jobnow.ui.dashboard.adapter.DashboardApplicantAdapter
import com.ac.jobnow.ui.jobDetails.SimilarJobsFragmentViewModel
import com.ac.jobnow.utils.OnJobItemClick

class SimilarJobsFragment : Fragment(), OnJobItemClick {

    private var similarJobsFragmentBinding: FragmentSimilarJobsBinding? = null
    private val binding get() = similarJobsFragmentBinding!!
    private val jobs: ArrayList<Job> = arrayListOf()
    private val similarJobsFragmentViewModel: SimilarJobsFragmentViewModel by activityViewModels()
    private lateinit var designation: String
    private lateinit var vacancyNumber: String

    companion object {
        @JvmStatic
        fun newInstance(designation: String, vacancyNumber: String): SimilarJobsFragment {
            val fragment = SimilarJobsFragment()
            val args = Bundle()
            args.putString("designation", designation)
            args.putString("vacancyNumber", vacancyNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vacancyNumber = arguments?.getString("vacancyNumber") ?: ""
        designation = arguments?.getString("designation") ?: ""
        similarJobsFragmentBinding = FragmentSimilarJobsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerViewAdapter()
        updateDataInAdapter()
    }

    private fun initRecyclerViewAdapter() {
        binding.fragmentSimilarJobsRecyclerView.apply {
            adapter = DashboardApplicantAdapter(jobs, this@SimilarJobsFragment)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateDataInAdapter() {
        similarJobsFragmentViewModel.apply {
            getJobs()
            jobsResult.observe(viewLifecycleOwner, {
                jobs.clear()
                jobs.addAll(it.filterJobs())
                binding.fragmentSimilarJobsRecyclerView.adapter?.notifyDataSetChanged()
            })
        }
    }

    private fun ArrayList<Job>.filterJobs(): List<Job> {
        return this.filter { it.designation == designation && it.vacancyNumber != vacancyNumber }
    }

    override fun onJobClick(position: Int) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToJobDetailsFragment(jobs[position])
        findNavController().navigate(action)
    }
}