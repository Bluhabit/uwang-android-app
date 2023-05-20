/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetCalculateEmergencyFund
import com.bluehabit.budgetku.android.components.HeaderStepWithProgress
import com.bluehabit.budgetku.android.components.ScreenInputFeedback
import com.bluehabit.budgetku.android.components.ScreenInputSuccess
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenDetailSaving
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputCategory
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputGoalsName
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputSavingAccount

object CreateAccountSaving {
    const val routeName = "CreateAccountSaving"
}

fun NavGraphBuilder.routeCreateAccountSaving(
    state: ApplicationState,
) {
    composable(CreateAccountSaving.routeName) {
        ScreenCreateAccountSaving(appState = state)
    }
}

@Composable
internal fun ScreenCreateAccountSaving(
    appState: ApplicationState,
) = UIWrapper<CreateAccountSavingViewModel>(appState = appState) {

    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState) {
        setupBottomSheet {
            BottomSheetCalculateEmergencyFund()
        }
    }
    BackHandler {
        dispatch(CreateAccountSavingEvent.Prev)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 20.dp
            ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 50.dp
                )

        ) {
            when (state.screenType) {
                0 -> ScreenInputCategory(
                    categories = dataState.categories,
                    selected = state.selectedCategory,
                    onSelected = {
                        commit {
                            copy(
                                selectedCategory = it
                            )
                        }
                    }
                )

                1 -> ScreenInputGoalsName(
                    value = state.savingPurpose,
                    onChange = {
                        commit {
                            copy(
                                savingPurpose = it
                            )
                        }
                    }
                )

                2 -> ScreenInputSavingAccount(
                    accounts = dataState.accounts,
                    selected = state.selectedAccount,
                    onSelectedAccount = {
                        commit {
                            copy(
                                selectedAccount = it
                            )
                        }
                    },
                    onAddAccount = {
                        navigateSingleTop(CreateAccount.routeName)
                    }
                )

                3 -> ScreenDetailSaving(
                    target = state.target,
                    amount = state.amount.toString(),
                    onAddAmount = {
                        commit {
                            copy(
                                screenType = 6
                            )
                        }
                    },
                    onSelectTarget = {
                        commit {
                            copy(
                                target = it
                            )
                        }
                    },
                )

                4 -> ScreenInputSuccess(
                    title = "Yay, berhasil tambah tabungan!",
                    subtitle = "Kamu suka tambah tabungan?",
                    onSubmit = {
                        commit {
                            copy(
                                screenType = 5
                            )
                        }
                    },
                    onDismiss = {
                        navigateUp()
                    }
                )

                5 -> ScreenInputFeedback(
                    title = "Wow, apa sih yang bikin kamu suka sama tabungan?",
                    onSubmit = {
                        navigateUp()
                    },
                    onDismiss = {
                        navigateUp()
                    }
                )

                6 -> ScreenNumPad(
                    value = "0",
                    onChange = {},
                    onSubmit = {},
                    onClear = {},
                    onRemove = {},
                    onDismiss = {
                        commit {
                            copy(
                                screenType = 3
                            )
                        }
                    }
                )
            }
        }

        if (state.screenType in 0..3) {
            HeaderStepWithProgress(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                total = 4,
                progress = state.screenType,
                onClose = {
                    navigateUp()
                },
                onBackPress = {
                    dispatch(CreateAccountSavingEvent.Prev)
                }
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        horizontal = 20.dp
                    )
            ) {
                ButtonPrimary(
                    text = "Lanjut"
                ) {
                    dispatch(CreateAccountSavingEvent.Next)

                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenCreateAccountSaving() {
    BaseMainApp {
        ScreenCreateAccountSaving(it)
    }
}