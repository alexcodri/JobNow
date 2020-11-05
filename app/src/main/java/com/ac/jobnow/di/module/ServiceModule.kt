package com.ac.jobnow.di.module

import com.ac.jobnow.repository.network.ServiceBuilder
import com.ac.jobnow.repository.network.requests.LoginService
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
    fun provideUserService(builder: ServiceBuilder): LoginService = builder.buildService(LoginService::class.java)
}