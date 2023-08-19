/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.repositories.ResetPasswordRepository
import com.bluehabit.eureka.data.common.Response
import javax.inject.Inject

class RequestResetPasswordUseCase @Inject constructor(
    private val resetPasswordRepository: ResetPasswordRepository
) {
    suspend operator fun invoke(email: String): Response<Any> =
        resetPasswordRepository.requestResetPassword(email)
}