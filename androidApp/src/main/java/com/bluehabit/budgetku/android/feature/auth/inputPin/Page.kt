/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.inputPin

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper

object InputPin {
    const val routeName = "InputPin"
}

fun NavGraphBuilder.routeInputPin(
    state: ApplicationState,
) {
    composable(InputPin.routeName) {
        ScreenInputPin(appState = state)
    }
}

@Composable
internal fun ScreenInputPin(
    appState: ApplicationState,
) = UIWrapper<InputPinViewModel>(appState = appState) {
    Column {

    }
}

@Preview
@Composable
fun PreviewScreenInputPin() {
    BaseMainApp {
        ScreenInputPin(it)
    }
}
