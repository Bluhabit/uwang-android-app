/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.home

import com.bluehabit.core.ui.R

data class HomeState(
    val currentScreen: Int = 0,
    val bottomNavigationItems: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            icon = R.drawable.ic_home
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_search
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_plus_square
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_notification
        ),
        BottomNavigationItem(
            icon = {}
        ),
    ),
    // Main
    val username: String = "",
    val fullName: String? = null,
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
    val joinDate: String? = null
)

data class BottomNavigationItem(
    val icon: Any
)
