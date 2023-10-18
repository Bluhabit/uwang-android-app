/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.feature.dashboard.model

import com.bluehabit.blu.feature.dashboard.DashboardAction

data class ItemTabListTask(
    val title: String,
    val action: DashboardAction
)
