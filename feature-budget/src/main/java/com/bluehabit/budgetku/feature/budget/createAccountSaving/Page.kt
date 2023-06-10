/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccountSaving

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenDetailSaving
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputCategory
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputGoalsName
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputSavingAccount
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.components.HeaderStepWithProgress
import com.bluehabit.core.ui.components.ScreenInputFeedback
import com.bluehabit.core.ui.components.ScreenInputSuccess
import com.bluehabit.core.ui.components.ScreenNumPad
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetCalculateEmergencyFund
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Grey500


@Composable
internal fun ScreenCreateAccountSaving(
    state: CreateAccountSavingState = CreateAccountSavingState(),
    data: CreateAccountSavingDataState = CreateAccountSavingDataState(),
    invoker: UIListenerData<CreateAccountSavingState, CreateAccountSavingDataState, CreateAccountSavingEvent>
) = UiWrapperData(invoker) {

    fun onBackPressed() {
        if (state.screenType == 1) {
            commit {
                copy(
                    bottomSheetType = CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION
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

    BaseScreen(
        controller = invoker.controller,
        bottomSheet = {
            when (state.bottomSheetType) {
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
    ) {

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
                        top = if (state.screenType in 1..4) 50.dp else 0.dp
                    )

            ) {
                when (state.screenType) {
                    1 -> {
                        ScreenInputCategory(
                            categories = data.categories,
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
                        ScreenInputSavingAccount(
                            accounts = data.accounts,
                            selected = state.selectedAccount,
                            onSelectedAccount = {
                                commit {
                                    copy(
                                        selectedAccount = it
                                    )
                                }
                            },
                            onAddAccount = {
                                navigateSingleTop(Routes.CreateAccount.routeName)
                            }
                        )
                    }

                    4 -> {
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
                                        bottomSheetType = CreateAccountSavingBottomSheetType.EMERGENCY_FUND
                                    )
                                }
                                showBottomSheet()
                            }
                        )
                    }

                    5 -> {
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
                    color = MaterialTheme.colors.primary,
                    onClose = {
                        commit {
                            copy(
                                bottomSheetType = CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION
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


}

@Preview
@Composable
fun PreviewScreenCreateAccountSaving() {
    BaseMainApp {
        ScreenCreateAccountSaving(
            invoker = UIListenerData(
                controller = it, state = CreateAccountSavingState(), data = CreateAccountSavingDataState()
            )
        )
    }
}