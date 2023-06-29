/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialResponse (
    val userId: String,
    val userEmail: String,
    val userAuthProvider: String,
    val userStatus: String,
    val userPermission: List<UserPermissionResponse>,
    val userProfile: UserProfileResponse,
    val createdAt: String,
    val updatedAt: String
)
