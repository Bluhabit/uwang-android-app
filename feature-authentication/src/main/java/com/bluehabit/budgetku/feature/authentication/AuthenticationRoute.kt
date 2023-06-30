/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication

import app.trian.mvi.NavigationGroup
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes

@NavigationGroup(
    route = AuthenticationConstants.parentRoute,
    startDestination = Routes.Splash.routeName
)
interface AuthenticationRoute