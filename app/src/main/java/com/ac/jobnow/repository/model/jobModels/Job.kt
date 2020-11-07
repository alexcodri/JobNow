package com.ac.jobnow.repository.model.jobModels

import android.os.Parcelable
import com.ac.jobnow.repository.model.registerModels.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Job(
    var _id: String,
    var jobTitle: String,
    var vacancyNumber: String,
    var companyName: String,
    var datePosted: String,
    var designation: String,
    var phoneNumber: String,
    var email: String,
    var website: String,
    var address: String,
    var jobDescription: String,
    var jobSkill: Array<String>,
    var salary: String,
    var applicants: List<User?>?
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Job

        if (_id != other._id) return false
        if (jobTitle != other.jobTitle) return false
        if (vacancyNumber != other.vacancyNumber) return false
        if (companyName != other.companyName) return false
        if (datePosted != other.datePosted) return false
        if (designation != other.designation) return false
        if (phoneNumber != other.phoneNumber) return false
        if (email != other.email) return false
        if (website != other.website) return false
        if (address != other.address) return false
        if (jobDescription != other.jobDescription) return false
        if (!jobSkill.contentEquals(other.jobSkill)) return false
        if (salary != other.salary) return false
        if (applicants != other.applicants) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + jobTitle.hashCode()
        result = 31 * result + vacancyNumber.hashCode()
        result = 31 * result + companyName.hashCode()
        result = 31 * result + datePosted.hashCode()
        result = 31 * result + designation.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + website.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + jobDescription.hashCode()
        result = 31 * result + jobSkill.contentHashCode()
        result = 31 * result + salary.hashCode()
        result = 31 * result + applicants.hashCode()
        return result
    }
}