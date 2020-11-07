package com.ac.jobnow.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ac.jobnow.R
import com.ac.jobnow.repository.model.jobModels.Job
import com.ac.jobnow.utils.OnJobItemClick
import kotlinx.android.synthetic.main.dashboard_listing_item.view.*

class DashboardApplicantAdapter(
    private var jobs: ArrayList<Job>,
    private var onJobItemClick: OnJobItemClick
) : RecyclerView.Adapter<JobViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder =
        JobViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dashboard_listing_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.apply {
            bind(jobs[position])
            itemView.job_listing_apply_now_button.setOnClickListener {
                onJobItemClick.onJobClick(position)
            }
        }
    }

    override fun getItemCount(): Int = jobs.size
}

class JobViewHolder(
    private val jobView: View
) : RecyclerView.ViewHolder(jobView) {
    fun bind(job: Job) {
        jobView.job_listing_job_position_tv.text = job.jobTitle
        addDataToComponents(jobView.job_listing_job_location_tv, job.address, "Office No: ")
        addDataToComponents(jobView.job_listing_date_posted_tv, job.datePosted, "Date Posted :- ")
        jobView.job_listing_job_description_tv.text = job.jobDescription
    }

    private fun addDataToComponents(component: TextView, value: String, placeholder: String) {
        val componentText = placeholder + value
        component.text = componentText
    }
}