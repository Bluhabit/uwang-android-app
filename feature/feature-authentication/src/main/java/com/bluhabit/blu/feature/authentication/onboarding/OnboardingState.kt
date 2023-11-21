/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.feature.authentication.onboarding

import android.os.Parcelable
import app.trian.mvi.ui.internal.contract.MviState
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class OnboardingState(
    val isLoading: Boolean = true,
    override val effect: @RawValue OnboardingEffect = OnboardingEffect.Nothing
) : MviState<OnboardingEffect>(), Parcelable
