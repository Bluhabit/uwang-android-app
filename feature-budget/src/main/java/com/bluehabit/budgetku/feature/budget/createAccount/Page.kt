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
import com.bluehabit.budgetku.feature.budget.createAccount.screen.ScreenInputAccount
import com.bluehabit.budgetku.feature.budget.createAccount.screen.ScreenMainCreateAccount
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.components.ScreenNumPad
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation

@Composable
internal fun ScreenCreateAccount(
    state: CreateAccountState = CreateAccountState(),
    data: CreateAccountDataState = CreateAccountDataState(),
    invoker: UIListenerData<CreateAccountState, CreateAccountDataState, CreateAccountEvent>
) = UiWrapperData(invoker) {

    fun backPressed() {
        when (state.screenType) {
            ScreenType.INPUT_AMOUNT -> commit { copy(screenType = ScreenType.MAIN) }
            ScreenType.MAIN -> showBottomSheet()
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
                    hideBottomSheet()
                },
                onConfirm = {
                    hideBottomSheet()
                    navigateUp()
                }
            )
        }
    ) {
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
                selectedAccount = state.selectedAccountName,
                selectedAccountIcon = state.selectedAccountIcon,
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
                accounts = data.financialAccount,
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
        ScreenCreateAccount(
            invoker = UIListenerData(
                controller = it,
                state = CreateAccountState(),
                data = CreateAccountDataState()
            )
        )
    }
}