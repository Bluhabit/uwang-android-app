/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.datasource.remote.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("auth_provider")
    val authProvider: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("profile")
    val profile: List<UserProfileResponse>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("deleted")
    val deleted: Boolean
)

fun UserCredentialResponse.toMap(): Map<String, String> {
    val gson = Gson()
    return mapOf(
        "id" to id,
        "authProvider" to authProvider,
        "email" to email,
        "fullName" to fullName,
        "username" to username,
        "dateOfBirth" to dateOfBirth,
        "gender" to gender,
        "profile" to gson.toJson(profile),
        "status" to status,
        "createdAt" to createdAt,
        "updatedAt" to updatedAt
    )
}

fun Map<String, String>.toUserCredential(): UserCredentialResponse {
    val profile = get("profile") ?: "[]"
    val gson = Gson()
    val extract = gson.fromJson<List<UserProfileResponse>>(profile, object : TypeToken<List<UserProfileResponse>>() {}.type)
    return UserCredentialResponse(
        id = get("id").orEmpty(),
        authProvider = get("authProvider").orEmpty(),
        email = get("email").orEmpty(),
        fullName = get("fullName").orEmpty(),
        username = get("username").orEmpty(),
        dateOfBirth = get("dateOfBirth").orEmpty(),
        gender = get("gender").orEmpty(),
        profile = extract,
        status = get("status").orEmpty(),
        createdAt = get("createdAt").orEmpty(),
        updatedAt = get("updatedAt").orEmpty(),
        deleted = false
    )
}