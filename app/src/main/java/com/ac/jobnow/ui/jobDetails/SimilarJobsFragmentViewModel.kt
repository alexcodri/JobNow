package com.ac.jobnow.ui.jobDetails

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ac.jobnow.repository.network.requests.JobService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SimilarJobsFragmentViewModel @ViewModelInject constructor(
    application: Application,
    private val jobService: JobService
) : AndroidViewModel(application) {
    private val job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    var jobsResult: MutableLiveData<ArrayList<com.ac.jobnow.repository.model.jobModels.Job>> =
        MutableLiveData()

    fun getJobs() {
        CoroutineScope(coroutineContext).launch {
            jobsResult.postValue(
                jobService.getAllJobs()
                    .body()?.jobs as ArrayList<com.ac.jobnow.repository.model.jobModels.Job>
            )
        }
    }
}