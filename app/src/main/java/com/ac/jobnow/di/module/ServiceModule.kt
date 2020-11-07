package com.ac.jobnow.di.module

import com.ac.jobnow.repository.network.ServiceBuilder
import com.ac.jobnow.repository.network.requests.JobService
import com.ac.jobnow.repository.network.requests.LoginService
import com.ac.jobnow.repository.network.requests.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideUserLoginService(builder: ServiceBuilder): LoginService =
        builder.buildService(LoginService::class.java)

    @Provides
    @Singleton
    fun provideUserRegisterService(builder: ServiceBuilder): RegisterService =
        builder.buildService(RegisterService::class.java)

    @Provides
    @Singleton
    fun provideGetJobsService(builder: ServiceBuilder): JobService =
        builder.buildService(JobService::class.java)
}