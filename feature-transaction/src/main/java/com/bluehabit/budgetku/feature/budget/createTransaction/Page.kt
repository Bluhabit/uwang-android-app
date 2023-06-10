/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createTransaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.feature.budget.createTransaction.CreateTransactionBottomSheetType.CANCEL_CONFIRMATION
import com.bluehabit.budgetku.feature.budget.createTransaction.CreateTransactionBottomSheetType.CATEGORY
import com.bluehabit.budgetku.feature.budget.createTransaction.CreateTransactionBottomSheetType.DATE_PICKER
import com.bluehabit.budgetku.feature.budget.createTransaction.components.ScreenInputAccount
import com.bluehabit.budgetku.feature.budget.createTransaction.components.ScreenInputAmount
import com.bluehabit.budgetku.feature.budget.createTransaction.components.ScreenInputDateTransaction
import com.bluehabit.budgetku.feature.budget.createTransaction.components.ScreenInputTransactionNameAndCategory
import com.bluehabit.budgetku.feature.budget.createTransaction.components.ScreenInputTransactionType
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.components.HeaderStepWithProgress
import com.bluehabit.core.ui.components.ScreenInputFeedback
import com.bluehabit.core.ui.components.ScreenInputSuccess
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetAddBudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetDatePicker
import com.bluehabit.core.ui.components.bottomSheet.BudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.ItemBudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.ItemBudgetSubCategory
import com.bluehabit.core.ui.theme.Blue800

@Composable
fun ScreenCreateTransaction(
    state: CreateTransactionState = CreateTransactionState(),
    data: CreateTransactionDataState = CreateTransactionDataState(),
    invoker: UIListenerData<CreateTransactionState, CreateTransactionDataState, CreateTransactionEvent>
) = UiWrapperData(invoker) {

    fun onBackPressed() {
        if (state.step == 1) {
            commit {
                copy(
                    bottomSheetType = CANCEL_CONFIRMATION
                )
            }
            showBottomSheet()
        } else {
            dispatch(CreateTransactionEvent.PrevPage)
        }
    }
    BackHandler {
        onBackPressed()
    }

    BaseScreen(
        bottomSheet = {
            when (state.bottomSheetType) {
                CATEGORY -> {
                    BottomSheetAddBudgetCategory(
                        onDismiss = { hideBottomSheet() },
                        onSearch = {

                        },
                        popularBudgetCategories = listOf(
                            ItemBudgetCategory(
                                title = stringResource(id = R.string.text_category_food_drink),
                                icon = R.drawable.icon_food,
                            ),
                            ItemBudgetCategory(
                                title = stringResource(id = R.string.text_category_house),
                                icon = R.drawable.icon_house
                            ),
                            ItemBudgetCategory(
                                title = stringResource(id = R.string.text_category_bill),
                                icon = R.drawable.icon_bill
                            )
                        ),
                        budgetCategories = listOf(
                            BudgetCategory(
                                title = stringResource(id = R.string.text_category_everyday_needs),
                                categories = listOf(
                                    ItemBudgetCategory(
                                        title = stringResource(id = R.string.text_category_food_drink),
                                        icon = R.drawable.icon_food,
                                        subCategories = listOf(
                                            ItemBudgetSubCategory(
                                                title = stringResource(id = R.string.text_category_restaurant),
                                                icon = R.drawable.icon_restaurant
                                            ),
                                            ItemBudgetSubCategory(
                                                title = stringResource(id = R.string.text_category_food_delivery),
                                                icon = R.drawable.icon_food_delivery
                                            ),
                                            ItemBudgetSubCategory(
                                                title = stringResource(id = R.string.text_category_coffee_shop),
                                                icon = R.drawable.icon_coffee
                                            ),
                                            ItemBudgetSubCategory(
                                                title = stringResource(id = R.string.text_category_fast_food),
                                                icon = R.drawable.icon_fast_food
                                            ),
                                        )
                                    ),
                                    ItemBudgetCategory(
                                        title = stringResource(id = R.string.text_category_fuel),
                                        icon = R.drawable.icon_bus
                                    )
                                )
                            ),
                            BudgetCategory(
                                title = stringResource(id = R.string.text_category_house_needs),
                                categories = listOf(
                                    ItemBudgetCategory(
                                        title = stringResource(id = R.string.text_category_house),
                                        icon = R.drawable.icon_house
                                    ),
                                    ItemBudgetCategory(
                                        title = stringResource(id = R.string.text_category_bill),
                                        icon = R.drawable.icon_bill
                                    )
                                )
                            ),
                        ),
                        onCategorySelected = {
                            commit {
                                copy(
                                    transactionCategory = it
                                )
                            }
                            hideBottomSheet()
                            dispatch(CreateTransactionEvent.NexPage)
                        },
                    )
                }

                DATE_PICKER -> {
                    BottomSheetDatePicker(
                        selectedDate = state.transactionDate,
                        onSelectDay = {
                            commit {
                                copy(
                                    transactionDate = it
                                )
                            }
                        },
                        onSubmit = {
                            hideBottomSheet()
                        }
                    )
                }

                CANCEL_CONFIRMATION -> {
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
                .background(Blue800)
        ) {
            when (state.step) {
                1 -> ScreenInputAmount(
                    amount = state.nominal,
                    onClear = {
                        dispatch(CreateTransactionEvent.ClearNominal)
                    },
                    onRemove = {
                        dispatch(CreateTransactionEvent.RemoveNominal)
                    },
                    onChange = {
                        dispatch(CreateTransactionEvent.Input(it))
                    },
                    onSubmit = {
                        dispatch(CreateTransactionEvent.NexPage)
                    }
                )

                2 -> ScreenInputTransactionType(
                    selected = stringResource(if (!state.isExpenses) R.string.text_transaction_type_income else R.string.text_transaction_type_expenses),
                    onSelectedType = {
                        commit {
                            copy(
                                isExpenses = it
                            )
                        }
//                        runSuspend {
//                            delay(500)
//                            dispatch(CreateTransactionEvent.NexPage)
//                        }
                    }
                )

                3 -> ScreenInputAccount(
                    transactionType = stringResource(
                        if (!state.isExpenses) R.string.text_transaction_type_income
                        else R.string.text_transaction_type_expenses
                    ),
                    selectedAccount = state.selectedAccount,
                    accounts = data.accounts,
                    onSelectedAccount = {
                        commit {
                            copy(
                                selectedAccount = it
                            )
                        }
//                        runSuspend {
//                            delay(500)
//                            dispatch(CreateTransactionEvent.NexPage)
//                        }
                    },
                    onAddAccount = {
                    //    navigateSingleTop(CreateAccount.routeName)
                    }
                )

                4 -> ScreenInputTransactionNameAndCategory(
                    transactionName = state.transactionName,
                    transactionCategory = state.transactionCategory,
                    onChange = {
                        commit {
                            copy(
                                transactionName = it
                            )
                        }
                    },
                    onSelectCategory = {
                        hideKeyboard()
                        commit {
                            copy(
                                bottomSheetType = CATEGORY
                            )
                        }
                        showBottomSheet()
                    },
                )

                5 -> ScreenInputDateTransaction(
                    date = state.transactionDate,
                    onSelectDate = {
                        dispatch(CreateTransactionEvent.ChangeBottomSheet(DATE_PICKER))
                        showBottomSheet()
                    },
                    onAddMore = {
                        dispatch(CreateTransactionEvent.AddMoreTransaction)
                    },
                    onSave = {
//                        runSuspend {
//                            delay(500)
//                            dispatch(CreateTransactionEvent.NexPage)
//                        }
                    }
                )

                6 -> ScreenInputSuccess(
                    title = stringResource(R.string.text_title_success_create_transaction),
                    subtitle = stringResource(R.string.text_sub_title_like_create_transaction),
                    onSubmit = {
                        dispatch(CreateTransactionEvent.NexPage)
                    }
                )

                7 -> ScreenInputFeedback(
                    title = stringResource(R.string.text_title_feedback_create_transaction),
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

                else -> ScreenInputAmount()
            }

            if (state.step in 1..5) {
                HeaderStepWithProgress(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    iconColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    color = MaterialTheme.colors.onPrimary,
                    total = 5,
                    progress = state.step,
                    onBackPress = {
                        if (state.step > 1) {
                            dispatch(CreateTransactionEvent.PrevPage)
                        } else {
                            onBackPressed()
                        }
                    },
                    onClose = {
                        commit {
                            copy(
                                bottomSheetType = CANCEL_CONFIRMATION
                            )
                        }
                        showBottomSheet()
                    }
                )
            }
        }
    }


}

@Preview
@Composable
fun PreviewScreenCreateTransaction() {
    BaseMainApp {
        ScreenCreateTransaction(
            invoker = UIListenerData(
                controller = it,
                state = CreateTransactionState(),
                data = CreateTransactionDataState()
            )
        )
    }
}