package com.ac.jobnow.ui.login

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ac.jobnow.repository.model.LoginRequest
import com.ac.jobnow.repository.model.LoginResponse
import com.ac.jobnow.repository.network.requests.LoginService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginFragmentViewModel @ViewModelInject constructor(
    application: Application,
    private val loginService: LoginService
) : AndroidViewModel(application) {
    private val job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var loginResult: MutableLiveData<LoginResponse> = MutableLiveData()

    fun login(loginRequest: LoginRequest) {
        CoroutineScope(coroutineContext).launch {
            loginResult.postValue(
                loginService.login(loginRequest.email, loginRequest.password).body()
            )
        }
    }
}