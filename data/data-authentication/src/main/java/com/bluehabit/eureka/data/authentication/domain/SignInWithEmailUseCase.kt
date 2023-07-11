/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.authentication.domain

import com.bluehabit.eureka.data.authentication.repositories.SignInWithEmailRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val signInWithEmailRepository: SignInWithEmailRepository
) {
}