/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.model

import user.USER

data class UserResponse(
    var userId: String? = null,
    var userEmail: String,
    var userAuthProvider: String,
    var userPhoneNumber:String,
    var userStatus: String,
    var userPermission: List<PermissionResponse>,
    var userProfile: UserProfileResponse?,
    var createdAt: String,
    var updatedAt: String,
)

data class UserProfileResponse(
    var userId: String? = null,
    var userFullName: String,
    var userCountryCode: String,
    var userDateOfBirth: String,
    var userProfilePicture: String,
    var createdAt: String,
    var updatedAt: String,
)

fun UserResponse.toEntity():USER{
    return USER(
        userId = userId.orEmpty(),
        userFullName = userProfile?.userFullName.orEmpty(),
        userCountryCode = userProfile?.userCountryCode.orEmpty(),
        userEmail = userEmail,
        userDateOfBirth = userProfile?.userDateOfBirth.orEmpty(),
        userPhoneNumber = userPhoneNumber,
        userProfilePicture = userProfile?.userProfilePicture.orEmpty(),
        userStatus = userStatus,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}