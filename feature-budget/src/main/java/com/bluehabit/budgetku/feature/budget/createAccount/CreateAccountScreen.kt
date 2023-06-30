/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccount

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import com.bluehabit.budgetku.feature.budget.createAccount.components.ScreenType
import com.bluehabit.budgetku.feature.budget.createAccount.screen.ScreenInputAccount
import com.bluehabit.budgetku.feature.budget.createAccount.screen.ScreenMainCreateAccount
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.components.ScreenNumPad
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.routes.Routes

@Navigation(
    route = Routes.CreateAccount.routeName,
    viewModel = CreateAccountViewModel::class
)
@Composable
internal fun CreateAccountScreen(
    uiContract: UIContract<CreateAccountState, CreateAccountIntent, CreateAccountAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(uiContract) {

    fun backPressed() {
        when (state.screenType) {
            ScreenType.INPUT_AMOUNT -> commit { copy(screenType = ScreenType.MAIN) }
            ScreenType.MAIN -> {}//showBottomSheet()
            ScreenType.SELECT_ACCOUNT -> commit { copy(screenType = ScreenType.MAIN) }
        }
    }

    BackHandler {
        backPressed()
    }

    BaseScreen(
        bottomSheet = {
            BottomSheetConfirmation(
                title = "Yakin membatalkan perubahan?",
                message = "Data yang sudah kamu ubah belum tersimpan dan akan hilang.",
                textConfirmation = "Yakin",
                textCancel = "Batal",
                onDismiss = {
                   // hideBottomSheet()
                },
                onConfirm = {
                    //hideBottomSheet()
                    navigator.navigateUp()
                }
            )
        }
    ) {
        when (state.screenType) {
            ScreenType.INPUT_AMOUNT -> ScreenNumPad(
                value = state.nominal,
                onChange = {
                    dispatch(CreateAccountAction.InputNominal(it))
                },
                onRemove = {
                    dispatch(CreateAccountAction.RemoveNominal)
                },
                onClear = {
                    dispatch(CreateAccountAction.ClearNominal)
                },
                onDismiss = {
                    commit { copy(screenType = ScreenType.MAIN) }
                },
                onSubmit = {
                    commit { copy(screenType = ScreenType.MAIN) }
                }
            )

            ScreenType.MAIN -> ScreenMainCreateAccount(
                selectedAccount = state.selectedAccountName,
                selectedAccountIcon = state.selectedAccountIcon,
                amount = state.nominal,
                onSubmit = {
                    navigator.navigateUp()
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
                accounts = state.financialAccount,
                onSelect = {
                    commit {
                        copy(
                            screenType = ScreenType.MAIN,
                            selectedAccountName = it.name,
                            selectedAccountIcon = it.icon
                        )
                    }
                },
                onBackPressed = {
                    commit { copy(screenType = ScreenType.MAIN) }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewScreenCreateAccount() {
    BaseMainApp {
        CreateAccountScreen(
              UIContract(
                controller = it,
                state = CreateAccountState()
            )
        )
    }
}