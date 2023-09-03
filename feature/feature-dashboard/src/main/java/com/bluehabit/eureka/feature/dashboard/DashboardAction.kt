/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

sealed interface DashboardAction {
    object SignOut: DashboardAction
    data class SetName(var name: String) : DashboardAction
}