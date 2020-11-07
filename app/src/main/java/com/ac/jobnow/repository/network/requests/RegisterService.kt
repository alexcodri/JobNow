package com.ac.jobnow.repository.network.requests

import com.ac.jobnow.repository.model.registerModels.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @FormUrlEncoded
    @POST("/createuser/")
    suspend fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("password") password: String,
        @Field("currentPosition") currentPosition: String,
        @Field("company") company: String,
        @Field("skills") skills: ArrayList<String>,
        @Field("isRecruiter") isRecruiter: Boolean
    ): Response<RegisterResponse>
}