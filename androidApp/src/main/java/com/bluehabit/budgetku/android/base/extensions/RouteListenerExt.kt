/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.base.extensions

import android.content.Context
import android.content.res.Configuration
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.components.menus

fun ApplicationState.listenChanges(
    ctx: Context,
    config: Configuration
) = this.router.addOnDestinationChangedListener { _, destination, _ ->
    currentRoute = destination.route.orEmpty()
    if (currentRoute in menus.map { it.route }) showBottomBar()
    else hideBottomBar()
}
