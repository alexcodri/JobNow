package com.ac.jobnow.repository.network.requests

import com.ac.jobnow.repository.model.applyToJobModels.ApplyResult
import com.ac.jobnow.repository.model.jobModels.JobResponse
import retrofit2.Response
import retrofit2.http.*

interface JobService {
    @GET("/jobs")
    suspend fun getAllJobs(): Response<JobResponse>

    @FormUrlEncoded
    @POST("/apply")
    suspend fun apply(
        @Field("userId") userId: String,
        @Field("jobId") jobId: String
    ): Response<ApplyResult>

    @GET("/userJobs")
    suspend fun getJobsForUser(
        @Query("userId") userId: String
    ): Response<JobResponse>
}