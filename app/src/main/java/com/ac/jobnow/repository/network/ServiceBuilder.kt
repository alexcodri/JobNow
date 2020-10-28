package com.ac.jobnow.repository.network

import retrofit2.Retrofit
import javax.inject.Inject

class ServiceBuilder @Inject constructor(private val retrofit: Retrofit){
     fun <T> buildService(service: Class<T>): T {
         return retrofit.create(service)
     }
}