package com.ac.jobnow.ui.appliedToJobs

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ac.jobnow.repository.model.jobModels.JobResponse
import com.ac.jobnow.repository.network.requests.JobService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AppliedToJobsViewModel @ViewModelInject constructor(
    application: Application,
    private val jobService: JobService
) : AndroidViewModel(application) {
    private val job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    val jobs: MutableLiveData<JobResponse> = MutableLiveData()

    fun getJobsForUser(userId: String) {
        CoroutineScope(coroutineContext).launch {
            jobs.postValue(
                jobService.getJobsForUser(userId).body()
            )
        }
    }

}