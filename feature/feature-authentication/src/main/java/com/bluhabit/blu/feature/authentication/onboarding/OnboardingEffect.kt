/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.feature.authentication.onboarding

sealed interface OnboardingEffect {
    object Nothing : OnboardingEffect
    object NavigateToHome:OnboardingEffect
}