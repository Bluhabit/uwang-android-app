/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.routes

object Routes {
    object Onboard {
        const val routeName = "Onboarding"
    }

    object SignUp {
        const val routeName = "SignUp"
        const val currentScreenArg = "currentScreen"
    }

    object ResetPassword {
        const val routeName = "ResetPassword"
        const val argDeeplink = "token"
        const val deepLink = "example://gawean/{$argDeeplink}"
        const val deepLink2 = "http://www.gawean.com/{$argDeeplink}"
        const val deepLink3 = "https://www.gawean.com/{$argDeeplink}"
    }

    object Auth {
        const val routeName = "Auth"
    }

    object Dashboard {
        const val routeName = "Dashboard"
    }

    object CreateTask {
        const val routeName = "CreateTask"
    }
}

