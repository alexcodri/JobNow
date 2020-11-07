package com.ac.jobnow.ui.jobDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.ac.jobnow.databinding.FragmentJobDetailsBinding
import com.ac.jobnow.repository.model.applyToJobModels.ApplyRequest
import com.ac.jobnow.repository.model.jobModels.Job
import com.google.android.material.snackbar.Snackbar


class JobDetailsFragment : Fragment() {

    private var jobDetailsBinding: FragmentJobDetailsBinding? = null
    private val binding get() = jobDetailsBinding!!
    private lateinit var job: Job
    private val args: JobDetailsFragmentArgs by navArgs()
    private val jobDetailsViewModel: JobDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        jobDetailsBinding = FragmentJobDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initJob()
        initComponents()
        handleApply()
        shareJob()
    }

    private fun handleApply() {
        val userId = activity?.getPreferences(Context.MODE_PRIVATE)?.getString("userId", "") ?: ""
        binding.applyNowJobDetailBtn.setOnClickListener {
            jobDetailsViewModel.apply(ApplyRequest(userId, job._id))
            jobDetailsViewModel.applyResult.observe(viewLifecycleOwner, {
                if (it.successfullyUpdated) {
                    Snackbar.make(
                        binding.rlJobDetails,
                        "Successfully applied!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.rlJobDetails,
                        "There was an error while applying to this job!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    private fun initComponents() {
        binding.apply {
            updateDataInComponent(jobDetailPositionTv, job.jobTitle)
            updateDataInComponent(jobDetailCompanyTv, job.companyName)
            addDataToComponent(jobDetailPostingNbTv, job.vacancyNumber)
            addDataToComponent(jobDetailDatePostedTv, job.datePosted)
            addDataToComponent(jobDetailDesignationTv, job.designation)
            addDataToComponent(jobDetailPhoneTv, job.phoneNumber)
            addDataToComponent(jobDetailEmailTv, job.email)
            addDataToComponent(jobDetailWebsiteTv, job.website)
            addDataToComponent(jobDetailAddressTv, job.address)
        }
    }

    private fun initJob() {
        job = args.job
    }

    private fun addDataToComponent(component: TextView, value: String) {
        val componentText = component.text.toString() + " " + value
        component.text = componentText
    }

    private fun updateDataInComponent(component: TextView, value: String) {
        component.text = value
    }

    private fun shareJob() {
        binding.toolbarDetail.shareJobToolbar.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareBody = composeShareBody()
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Job posting")
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    private fun composeShareBody(): String {
        return "Check out this " + job.jobTitle +
                " job at " + job.companyName +
                ": " + job.website
    }
}