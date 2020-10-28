package com.ac.jobnow.repository.remote.data

import com.ac.jobnow.repository.model.LoginRequest
import com.ac.jobnow.repository.network.requests.LoginService
import javax.inject.Inject

class LoginDataSource  @Inject constructor(private val loginService: LoginService){
    suspend fun login(loginRequest: LoginRequest){
        val loginData = loginService.login(loginRequest)
    }
}