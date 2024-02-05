/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.domain

import com.bluhabit.blu.android.data.authentication.repositories.UserRepository
import com.bluhabit.blu.data.common.Response
import javax.inject.Inject

class PersonalizeUpdateProfileLevelUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        level: String
    ): Response<Any> {
        return userRepository.updateLevel(level = level)

    }
}