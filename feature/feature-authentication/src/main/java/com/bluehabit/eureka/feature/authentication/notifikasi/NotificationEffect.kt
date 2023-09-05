/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.notifikasi

sealed interface  NotificationEffect {

    data class ShowAlert(val message:String): NotificationEffect

    object Nothing : NotificationEffect
}