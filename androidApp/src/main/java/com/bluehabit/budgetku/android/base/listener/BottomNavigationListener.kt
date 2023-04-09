/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.listener

import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu

interface BottomNavigationListener {
    fun onRefresh(item: DashboardBottomNavigationMenu)

    fun onNavigate(item: DashboardBottomNavigationMenu)

    fun onFab()
}