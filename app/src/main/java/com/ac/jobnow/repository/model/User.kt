package com.ac.jobnow.repository.model

data class User(
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val currentPosition: String,
    val company: String,
    val skills: List<String>
)