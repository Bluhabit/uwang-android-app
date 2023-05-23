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
import androidx.compose.material.MaterialTheme
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
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenDetailSaving
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputCategory
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputGoalsName
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputSavingAccount
import com.bluehabit.budgetku.android.ui.Grey500

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
            when(state.bottomSheetType){
                CreateAccountSavingBottomSheetType.EMERGENCY_FUND -> {
                    BottomSheetCalculateEmergencyFund()
                }
                CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION -> {
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

        }
    }

    fun onBackPressed() {
        if (state.screenType == 1) {
            commit {
                copy(
                    bottomSheetType=CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION
                )
            }
            showBottomSheet()
        }

        if (state.screenType > 1) {
            dispatch(CreateAccountSavingEvent.Prev)
        }
    }
    BackHandler {
        onBackPressed()
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
                    top =  if (state.screenType in 1..4) 50.dp else 0.dp
                )

        ) {
            when (state.screenType) {
                1 -> {
                    appState.hideTopAppBar()
                    ScreenInputCategory(
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
                }

                2 -> {
                    appState.hideTopAppBar()
                    ScreenInputGoalsName(
                        value = state.savingPurpose,
                        onChange = {
                            commit {
                                copy(
                                    savingPurpose = it
                                )
                            }
                        }
                    )
                }

                3 -> {
                    appState.hideTopAppBar()
                    ScreenInputSavingAccount(
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
                }

                4 -> {
                    appState.hideTopAppBar()
                    ScreenDetailSaving(
                        target = state.target,
                        amount = state.nominal,
                        onAddAmount = {
                            commit {
                                copy(
                                    screenType = 7
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
                        onShowBottomSheet = {
                            commit {
                                copy(
                                    bottomSheetType=CreateAccountSavingBottomSheetType.EMERGENCY_FUND
                                )
                            }
                            showBottomSheet()
                        }
                    )
                }

                5 -> {
                    appState.hideTopAppBar()
                    ScreenInputSuccess(
                        title = "Yay, berhasil tambah tabungan!",
                        subtitle = "Kamu suka tambah tabungan?",
                        onSubmit = {
                            commit {
                                copy(
                                    screenType = 6
                                )
                            }
                        },
                        onDismiss = {
                            navigateUp()
                        }
                    )
                }

                6 -> {
                    appState.hideTopAppBar()
                    ScreenInputFeedback(
                        title = "Wow, apa sih yang bikin kamu suka sama tabungan?",
                        feedback = state.feedback,
                        onChange = {
                            commit {
                                copy(
                                    feedback = it
                                )
                            }
                        },
                        onSubmit = {
                            navigateUp()
                        },
                        onDismiss = {
                            navigateUp()
                        }
                    )
                }

                7 -> {
                    appState.hideTopAppBar()
                    ScreenNumPad(
                        value = state.nominal,
                        onChange = {
                            dispatch(CreateAccountSavingEvent.Input(it))
                        },
                        onSubmit = {
                            commit {
                                copy(
                                    screenType = 4
                                )
                            }
                        },
                        onClear = {
                            dispatch(CreateAccountSavingEvent.Clear)
                        },
                        onRemove = {
                            dispatch(CreateAccountSavingEvent.Remove)
                        },
                        onDismiss = {
                            commit {
                                copy(
                                    screenType = 4
                                )
                            }
                        }
                    )
                }
            }
        }

        if (state.screenType in 1..4) {
            HeaderStepWithProgress(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                total = 4,
                progress = state.screenType,
                backgroundColor = Grey500,
                color=MaterialTheme.colors.primary,
                onClose = {
                    commit {
                        copy(
                            bottomSheetType=CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION
                        )
                    }
                    showBottomSheet()
                },
                onBackPress = {
                    onBackPressed()
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
                    hideKeyboard()
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