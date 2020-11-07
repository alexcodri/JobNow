package com.ac.jobnow.ui.appliedToJobs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ac.jobnow.R
import com.ac.jobnow.repository.model.jobModels.Job
import kotlinx.android.synthetic.main.dashboard_applicants_item.view.*

class AppliedToJobsAdapter(
    var jobs: ArrayList<Job>
) : RecyclerView.Adapter<AppliedToJobsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppliedToJobsViewHolder =
        AppliedToJobsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dashboard_applicants_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: AppliedToJobsViewHolder, position: Int) {
        holder.apply {
            bind(jobs[position])
        }
    }

    override fun getItemCount(): Int = jobs.size
}

class AppliedToJobsViewHolder(
    private val jobView: View
) : RecyclerView.ViewHolder(jobView) {
    fun bind(job: Job) {
        jobView.applicant_name_tv.text = job.jobTitle
        jobView.position_et.text = job.companyName
        jobView.applicant_position_tv.text = job.datePosted
    }
}
