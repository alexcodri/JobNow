package com.ac.jobnow.repository.model.applyToJobModels

data class ApplyRequest(
    val userId: String,
    val jobId: String,
)