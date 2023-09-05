/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.profile.domain

import com.bluehabit.eureka.data.profile.repositories.ProfileRepository
import java.io.File
import javax.inject.Inject

class UploadProfilePictureUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val file: File,
) {
    suspend operator fun invoke() = profileRepository.uploadProfilePicture(file)
}