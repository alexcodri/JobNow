package com.ac.jobnow.repository.model.registerModels

data class RegisterResponse(
    val isUniqueUser: Boolean,
    val isRecruiter: Boolean
)