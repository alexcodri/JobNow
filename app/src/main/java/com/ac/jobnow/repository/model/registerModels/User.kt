package com.ac.jobnow.repository.model.registerModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val currentPosition: String,
    val company: String,
    val skills: List<String>,
    val isRecruiter: Boolean
) : Parcelable
