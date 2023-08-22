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

class LinkConfirmationUseCase @Inject constructor(
    private val resetPasswordRepository: ResetPasswordRepository
) {
    suspend operator fun invoke(token: String): Response<Any> =
        resetPasswordRepository.linkConfirmation(token)
}