package com.ac.jobnow.repository.network.requests

import com.ac.jobnow.repository.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/login/")
    suspend fun login(@Body login: LoginRequest): Response<Any>
}