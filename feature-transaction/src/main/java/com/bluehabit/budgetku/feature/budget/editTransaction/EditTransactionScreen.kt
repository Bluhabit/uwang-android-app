/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.editTransaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import com.bluehabit.budgetku.feature.budget.editTransaction.components.BottomSheetTypeEditTransaction
import com.bluehabit.budgetku.feature.budget.editTransaction.components.ScreenMainEditTransaction
import com.bluehabit.budgetku.feature.budget.editTransaction.components.ScreenType
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.ScreenNumPad
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetAccount
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetAddBudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetDatePicker
import com.bluehabit.core.ui.components.bottomSheet.BudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.ItemBudgetCategory
import com.bluehabit.core.ui.components.bottomSheet.ItemBudgetSubCategory
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.routes.TransactionConstants


@Navigation(
    route = Routes.EditTransaction.routeName,
    group = TransactionConstants.parentRoute,
    viewModel = EditTransactionViewModel::class
)
@Composable
fun EditTransactionScreen(
    uiContract: UIContract<EditTransactionState, EditTransactionIntent, EditTransactionAction>
) = UIWrapper(uiContract) {

    fun onBacPressed() {
        when (state.screenType) {
            ScreenType.NUMPAD -> commit {
                copy(
                    screenType = ScreenType.MAIN
                )
            }

            ScreenType.MAIN -> {
                commit {
                    copy(
                        bottomSheetType = BottomSheetTypeEditTransaction.CANCEL_CONFORMATION,
                    )
                }
                //    showBottomSheet()
            }
        }
    }

    BackHandler {
        onBacPressed()
    }
    BaseScreen(
        topAppBar = {
            when (state.screenType) {
                ScreenType.NUMPAD -> Unit
                ScreenType.MAIN -> TopAppBar(
                    modifier = Modifier
                        .background(Color(0xFF1962FE))
                        .padding(20.dp),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier.clickable { navigator.navigateUp() }
                        )
                    },
                    elevation = 0.dp,
                    title = {
                        Text(text = stringResource(id = R.string.text_topbar_edit_transaction))
                    },
                )
            }
        },
        bottomSheet = {
            when (state.bottomSheetType) {
                BottomSheetTypeEditTransaction.DATE -> BottomSheetDatePicker(
                    selectedDate = state.transactionDate,
                    onSelectDay = {
                        commit {
                            copy(
                                transactionDate = it
                            )
                        }
                    },
                    onSubmit = {
                        //hideBottomSheet()
                    }
                )

                BottomSheetTypeEditTransaction.CATEGORY -> BottomSheetAddBudgetCategory(
                    onDismiss = {
                        //  hideBottomSheet()
                    },
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
                        // hideBottomSheet()
                    },
                )

                BottomSheetTypeEditTransaction.ACCOUNT -> BottomSheetAccount(
                    accounts = state.accounts,
                    selectedAccount = state.accountId,
                    onSelectedAccount = {
                        // hideBottomSheet()
                        commit {
                            copy(
                                accountName = it.accountName,
                                accountIcon = it.icon,
                                accountId = it.id
                            )
                        }
                    },
                    onSubmit = {
                        // hideBottomSheet()
                    }
                )

                BottomSheetTypeEditTransaction.CANCEL_CONFORMATION -> {
                    BottomSheetConfirmation(
                        title = "Yakin membatalkan perubahan?",
                        message = "Data yang sudah kamu ubah belum tersimpan dan akan hilang",
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
        when (state.screenType) {
            ScreenType.NUMPAD -> {
                ScreenNumPad(
                    value = state.nominal,
                    onSubmit = {
                        commit {
                            copy(
                                screenType = ScreenType.MAIN
                            )
                        }
                    },
                    onChange = {
                        dispatch(EditTransactionAction.InputNominal(it))
                    },
                    onDismiss = {
                        commit {
                            copy(
                                screenType = ScreenType.MAIN
                            )
                        }
                    },
                    onClear = {
                        dispatch(EditTransactionAction.ClearNominal)
                    },
                    onRemove = {
                        dispatch(EditTransactionAction.ClearNominal)
                    }
                )
            }

            ScreenType.MAIN -> {
                ScreenMainEditTransaction(
                    isExpenses = state.isExpenses,
                    onChangeTransactionType = {
                        commit {
                            copy(
                                isExpenses = it
                            )
                        }
                    },
                    categoryName = state.categoryName,
                    categoryIcon = state.categoryIcon,
                    onSelectCategory = {
                        commit {
                            copy(
                                bottomSheetType = BottomSheetTypeEditTransaction.CATEGORY
                            )
                        }

                        //showBottomSheet()
                    },
                    bankAccountName = state.accountName,
                    bankAccountIcon = state.accountIcon,
                    onSelectAccount = {
                        commit {
                            copy(
                                bottomSheetType = BottomSheetTypeEditTransaction.ACCOUNT
                            )
                        }
                        // showBottomSheet()
                    },
                    transactionDate = state.transactionDate.toString(),
                    onSelectTransactionDate = {
                        commit {
                            copy(
                                bottomSheetType = BottomSheetTypeEditTransaction.DATE
                            )
                        }
                        // showBottomSheet()
                    },
                    amount = state.nominal,
                    onInputAmount = {
                        commit {
                            copy(
                                screenType = ScreenType.NUMPAD
                            )
                        }
                    },
                    onSubmit = {
                        navigator.navigateUp()
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenEditTransaction() {
    BaseMainApp {
        EditTransactionScreen(
            uiContract = UIContract(
                controller = it,
                state = EditTransactionState(),
            )
        )
    }
}