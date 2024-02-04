/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.android.data.authentication.datasource.remote.response.UserCredentialResponse
import com.bluhabit.blu.android.data.authentication.repositories.AuthRepository
import com.bluhabit.blu.android.data.authentication.repositories.UserRepository
import com.bluhabit.blu.data.common.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PersonalizeUpdateProfileUsernameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        username: String
    ): Response<Any> {
        return userRepository.updateUsername(username=username)

    }
}