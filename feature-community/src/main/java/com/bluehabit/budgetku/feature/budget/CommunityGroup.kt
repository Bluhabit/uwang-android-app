/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget

import app.trian.mvi.NavigationGroup
import com.bluehabit.core.ui.routes.CommunityConstants
import com.bluehabit.core.ui.routes.Routes

@NavigationGroup(
    route = CommunityConstants.parentRoute,
    startDestination = Routes.CreatePost.routeName
)
interface CommunityGroup