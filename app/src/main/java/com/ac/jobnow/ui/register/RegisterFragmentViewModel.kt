package com.ac.jobnow.ui.register

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ac.jobnow.repository.model.registerModels.RegisterResponse
import com.ac.jobnow.repository.model.registerModels.User
import com.ac.jobnow.repository.network.requests.RegisterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RegisterFragmentViewModel @ViewModelInject constructor(
    application: Application,
    private val registerService: RegisterService
) : AndroidViewModel(application) {
    private val job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var registerResult: MutableLiveData<RegisterResponse> = MutableLiveData()

    fun register(user: User) {
        CoroutineScope(coroutineContext).launch {
            registerResult.postValue(
                registerService.register(
                    user.email,
                    user.name,
                    user.surname,
                    user.password,
                    user.currentPosition,
                    user.company,
                    user.skills as ArrayList<String>,
                    user.isRecruiter
                ).body()
            )
        }
    }
}