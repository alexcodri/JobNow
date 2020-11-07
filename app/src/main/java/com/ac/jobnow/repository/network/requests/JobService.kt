package com.ac.jobnow.repository.network.requests

import com.ac.jobnow.repository.model.applyToJobModels.ApplyResult
import com.ac.jobnow.repository.model.jobModels.JobResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface JobService {
    @GET("/jobs")
    suspend fun getAllJobs(): Response<JobResponse>

    @FormUrlEncoded
    @POST("/apply")
    suspend fun apply(
        @Field("userId") userId: String,
        @Field("jobId") jobId: String
    ): Response<ApplyResult>
}