/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard

import java.time.OffsetDateTime

sealed interface DashboardAction {
    object SignOut : DashboardAction
    data class SetName(var name: String) : DashboardAction

    object GetListTask : DashboardAction
    data class GetListTaskByDate(
        val fromDate: OffsetDateTime,
        val toDate: OffsetDateTime
    ) : DashboardAction
}