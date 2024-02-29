/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.home

data class HomeState(
    // Main
    val username: String = "",
    val fullName: String = "",
    val imageProfileUrl: String? = null,
    val bioProfile: String? = null,
    val websiteProfile: String? = null,
    val currentPoint: Int = 0,
    val sizePoint: Int = 500,
    val topicList: List<String> = listOf(),
    val completedStep: Int = 0,
    val sizeStep: Int = 4,
    val sizePost: Int = 0,
    val sizeFollowers: Int = 0,
    val sizeFollowing: Int = 0,
    val joinDate: String = ""
)
