/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAccount
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAddBudgetCategory
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetDatePicker
import com.bluehabit.budgetku.android.components.bottomSheet.BudgetCategory
import com.bluehabit.budgetku.android.components.bottomSheet.ItemBudgetCategory
import com.bluehabit.budgetku.android.components.bottomSheet.ItemBudgetSubCategory
import com.bluehabit.budgetku.android.feature.editTransaction.components.ScreenMainEditTransaction

object EditTransaction {
    const val routeName = "EditTransaction"
}

fun NavGraphBuilder.routeEditTransaction(
    state: ApplicationState,
) {
    composable(EditTransaction.routeName) {
        ScreenEditTransaction(appState = state)
    }
}

@Composable
internal fun ScreenEditTransaction(
    appState: ApplicationState,
) = UIWrapper<EditTransactionViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()
    with(appState) {
        setupTopAppBar {
            TopAppBar(
                modifier = Modifier
                    .background(Color(0xFF1962FE))
                    .padding(20.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable { navigateUp() }
                    )
                },
                elevation = 0.dp,
                title = {
                    Text(text = stringResource(id = R.string.text_topbar_edit_transaction))
                },
            )
        }
        setupBottomSheet {
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
                        hideBottomSheet()
                    }
                )

                BottomSheetTypeEditTransaction.CATEGORY -> BottomSheetAddBudgetCategory(
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
                        hideBottomSheet()
                    },
                )

                BottomSheetTypeEditTransaction.ACCOUNT -> BottomSheetAccount(
                    accounts = dataState.accounts,
                    selectedAccount = state.accountId,
                    onSelectedAccount = {
                        hideBottomSheet()
                        commit {
                            copy(
                                accountName = it.accountName,
                                accountIcon = it.icon,
                                accountId = it.id
                            )
                        }
                    },
                    onSubmit = {
                        hideBottomSheet()
                    }
                )

                BottomSheetTypeEditTransaction.CANCEL_CONFORMATION -> {
                    BottomSheetConfirmation(
                        title = "Yakin membatalkan perubahan?",
                        message = "Data yang sudah kamu ubah belum tersimpan dan akan hilang",
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
                showBottomSheet()
            }
        }
    }

    BackHandler {
        onBacPressed()
    }
    when (state.screenType) {
        ScreenType.NUMPAD -> {
            appState.hideTopAppBar()
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
                    dispatch(EditTransactionEvent.InputNominal(it))
                },
                onDismiss = {
                    commit {
                        copy(
                            screenType = ScreenType.MAIN
                        )
                    }
                },
                onClear = {
                    dispatch(EditTransactionEvent.ClearNominal)
                },
                onRemove = {
                    dispatch(EditTransactionEvent.ClearNominal)
                }
            )
        }

        ScreenType.MAIN -> {
            appState.showTopAppBar()
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

                    showBottomSheet()
                },
                bankAccountName = state.accountName,
                bankAccountIcon = state.accountIcon,
                onSelectAccount = {
                    commit {
                        copy(
                            bottomSheetType = BottomSheetTypeEditTransaction.ACCOUNT
                        )
                    }
                    showBottomSheet()
                },
                transactionDate = state.transactionDate.toString(),
                onSelectTransactionDate = {
                    commit {
                        copy(
                            bottomSheetType = BottomSheetTypeEditTransaction.DATE
                        )
                    }
                    showBottomSheet()
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
                    navigateUp()
                }
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenEditTransaction() {
    BaseMainApp {
        ScreenEditTransaction(it)
    }
}