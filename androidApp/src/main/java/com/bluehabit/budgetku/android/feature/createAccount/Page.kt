/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.createAccount.screen.ScreenMainCreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.components.ScreenNumPad
import java.math.BigDecimal

object CreateAccount {
    const val routeName = "CreateAccount"
}

fun NavGraphBuilder.routeCreateAccount(
    state: ApplicationState,
) {
    composable(CreateAccount.routeName) {
        ScreenCreateAccount(appState = state)
    }
}

@Composable
internal fun ScreenCreateAccount(
    appState: ApplicationState,
) = UIWrapper<CreateAccountViewModel>(appState = appState) {
    val state by uiState.collectAsState()

    with(appState) {

    }


    when (state.screenType) {
        ScreenType.INPUT_AMOUNT -> ScreenNumPad(
            value = "",
            onChange = {},
            onDismiss = {
                commit {
                    copy(
                        screenType = ScreenType.MAIN
                    )
                }
            },
            onSubmit = {
                commit {
                    copy(
                        screenType = ScreenType.MAIN
                    )
                }
            },
            onRemove = {},
            onClear = {}
        )

        ScreenType.MAIN -> ScreenMainCreateAccount(
            selectedAccount = "Bank BCA",
            amount = BigDecimal(1_000_000),
            onSubmit = {},
            onInputAmount = {
                commit {
                    copy(
                        screenType = ScreenType.INPUT_AMOUNT
                    )
                }
            },
            onInputAccount = {
                commit {
                    copy(
                        screenType = ScreenType.SELECT_ACCOUNT
                    )
                }
            },
            onSubscribe = {}
        )

        ScreenType.SELECT_ACCOUNT -> {

        }
    }


}

@Preview
@Composable
fun PreviewScreenCreateAccount() {
    BaseMainApp {
        ScreenCreateAccount(it)
    }
}