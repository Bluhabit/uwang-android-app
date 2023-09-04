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

    object GetAllListTask : DashboardAction
    object GetListTaskToday : DashboardAction
    object GetListTaskTomorrow : DashboardAction
    object GetListTaskThisWeek : DashboardAction
    data class GetListTaskByStatus(val statusId:String="e3daf232-2b2c-4720-9e87-7d23e869f8e6") : DashboardAction
}