package com.ac.jobnow.repository.model.loginModels

data class LoginResponse(
    val isUserFound: Boolean,
    val isUserRecruiter: Boolean
)