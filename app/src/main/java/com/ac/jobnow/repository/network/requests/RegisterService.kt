package com.ac.jobnow.repository.network.requests

import com.ac.jobnow.repository.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/register/")
    suspend fun register(@Body user: User): Response<Any>
}