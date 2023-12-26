/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import android.graphics.Bitmap
import com.bluhabit.blu.android.data.authentication.datasource.remote.response.UserCredentialResponse
import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.data.common.Response
import javax.inject.Inject

class CompleteProfileUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        avatar: Bitmap,
        username: String,
        dateOfBirth: String,
        personalPreferences: List<String>
    ): Response<UserCredentialResponse> {
        return when (val uploadAvatar = authRepository.uploadAvatar(avatar)) {
            is Response.Error -> uploadAvatar
            is Response.Result -> {
                authRepository.updateProfile(
                    avatar = uploadAvatar.data,
                    username = username,
                    dateOfBirth = dateOfBirth,
                    personalPreferences = personalPreferences
                )
            }
        }
    }
}