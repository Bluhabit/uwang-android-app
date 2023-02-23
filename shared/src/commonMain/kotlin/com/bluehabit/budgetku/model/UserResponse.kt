package com.bluehabit.budgetku.model

data class UserResponse(
    val userId: String,
    val userFullName: String,
    val userCountryCode: String,
    val userDateOfBirth: String,
    val userEmail: String,
    val userAuthProvider: String,
    val userStatus: String,
    val createdAt: String,
    val updatedAt: String
)