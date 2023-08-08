/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.repositories.CheckSessionRepository
import com.bluehabit.eureka.data.common.Response
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val checkSessionRepository: CheckSessionRepository
) {
    suspend operator fun invoke(): Response<Boolean> = checkSessionRepository.execute()
}