/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.budgetku.android.feature.createAccount.screen.ScreenInputAccount
import com.bluehabit.budgetku.android.feature.createAccount.screen.ScreenMainCreateAccount

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
    val dataState by uiDataState.collectAsState()

    with(appState) {
        hideTopAppBar()
        setupBottomSheet {
            BottomSheetConfirmation(
                title = "Yakin membatalkan perubahan?",
                message = "Data yang sudah kamu ubah belum tersimpan dan akan hilang.",
                textConfirmation = "Yakin",
                textCancel = "Batal",
                onDismiss = {
                    hideBottomSheet()
                },
                onConfirm = {
                    hideBottomSheet()
                    navigateUp()
                }
            )
        }
    }
    
    fun backPressed(){
        when (state.screenType) {
            ScreenType.INPUT_AMOUNT -> commit { copy(screenType = ScreenType.MAIN) }
            ScreenType.MAIN -> showBottomSheet()
            ScreenType.SELECT_ACCOUNT -> commit { copy(screenType = ScreenType.MAIN) }
        }
    }

    BackHandler {
        backPressed()
    }
    when (state.screenType) {
        ScreenType.INPUT_AMOUNT -> ScreenNumPad(
            value = state.nominal,
            onChange = {
                dispatch(CreateAccountEvent.InputNominal(it))
            },
            onRemove = {
                dispatch(CreateAccountEvent.RemoveNominal)
            },
            onClear = {
                dispatch(CreateAccountEvent.ClearNominal)
            },
            onDismiss = {
                commit { copy(screenType = ScreenType.MAIN) }
            },
            onSubmit = {
                commit { copy(screenType = ScreenType.MAIN) }
            }
        )

        ScreenType.MAIN -> ScreenMainCreateAccount(
            selectedAccount = "Bank Jago",
            amount = state.nominal,
            onSubmit = {
                navigateUp()
            },
            onInputAmount = {
                commit { copy(screenType = ScreenType.INPUT_AMOUNT) }
            },
            onInputAccount = {
                commit { copy(screenType = ScreenType.SELECT_ACCOUNT) }
            },
            onSubscribe = {},
            onBackPressed = {
                backPressed()
            }
        )

        ScreenType.SELECT_ACCOUNT -> ScreenInputAccount(
            accounts = dataState.financialAccount,
            onSelect = {
                commit { copy(screenType = ScreenType.MAIN) }
            },
            onBackPressed = {
                commit { copy(screenType = ScreenType.MAIN) }
            }
        )
    }


}

@Preview
@Composable
fun PreviewScreenCreateAccount() {
    BaseMainApp {
        ScreenCreateAccount(it)
    }
}