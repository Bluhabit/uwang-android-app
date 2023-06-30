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
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.CreateAccountSavingBottomSheetType
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenDetailSaving
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputCategory
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputGoalsName
import com.bluehabit.budgetku.feature.budget.createAccountSaving.components.ScreenInputSavingAccount
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.components.HeaderStepWithProgress
import com.bluehabit.core.ui.components.ScreenInputFeedback
import com.bluehabit.core.ui.components.ScreenInputSuccess
import com.bluehabit.core.ui.components.ScreenNumPad
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetCalculateEmergencyFund
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Grey500


@Navigation(
    route = Routes.CreateAccountSaving.routeName,
    viewModel = CreateAccountSavingViewModel::class
)
@Composable
internal fun CreateAccountSavingScreen(
    uiContract: UIContract<CreateAccountSavingState, CreateAccountSavingIntent, CreateAccountSavingAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(uiContract) {

    fun onBackPressed() {
        if (state.screenType == 1) {
            commit {
                copy(
                    bottomSheetType = CreateAccountSavingBottomSheetType.CANCEL_CONFIRMATION
                )
            }
           // showBottomSheet()
        }

        if (state.screenType > 1) {
            dispatch(CreateAccountSavingAction.Prev)
        }
    }
    BackHandler {
        onBackPressed()
    }

    BaseScreen(
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
                           // hideBottomSheet()
                        },
                        onConfirm = {
                           // hideBottomSheet()
                           navigator.navigateUp()
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
                            categories = state.categories,
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
                            accounts = state.accounts,
                            selected = state.selectedAccount,
                            onSelectedAccount = {
                                commit {
                                    copy(
                                        selectedAccount = it
                                    )
                                }
                            },
                            onAddAccount = {
                                navigator.navigateSingleTop(Routes.CreateAccount.routeName)
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
                                //showBottomSheet()
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
                                navigator.navigateUp()
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
                                navigator.navigateUp()
                            },
                            onDismiss = {
                                navigator.navigateUp()
                            }
                        )
                    }

                    7 -> {
                        ScreenNumPad(
                            value = state.nominal,
                            onChange = {
                                dispatch(CreateAccountSavingAction.Input(it))
                            },
                            onSubmit = {
                                commit {
                                    copy(
                                        screenType = 4
                                    )
                                }
                            },
                            onClear = {
                                dispatch(CreateAccountSavingAction.Clear)
                            },
                            onRemove = {
                                dispatch(CreateAccountSavingAction.Remove)
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
                        //showBottomSheet()
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
                        //hideKeyboard()
                        dispatch(CreateAccountSavingAction.Next)
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
        CreateAccountSavingScreen(
              UIContract(
                controller = it, state = CreateAccountSavingState()
            )
        )
    }
}