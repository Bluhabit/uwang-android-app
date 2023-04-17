/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.BottomSheetAddBudgetCategory
import com.bluehabit.budgetku.android.components.BottomSheetDatePicker
import com.bluehabit.budgetku.android.components.BudgetCategory
import com.bluehabit.budgetku.android.components.ItemBudgetCategory
import com.bluehabit.budgetku.android.components.ItemBudgetSubCategory
import com.bluehabit.budgetku.android.components.ScreenInputFeedback
import com.bluehabit.budgetku.android.components.ScreenInputSuccess
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransactionBottomSheetType.DATE_PICKER
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransactionBottomSheetType.CATEGORY
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputAccount
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputAmount
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputDateTransaction
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputTransactionNameAndCategory
import com.bluehabit.budgetku.android.feature.createTransaction.components.ScreenInputTransactionType
import com.bluehabit.budgetku.android.ui.Blue800

object CreateTransaction {
    const val routeName = "CreateTransaction"
}

fun NavGraphBuilder.routeCreateTransaction(
    state: ApplicationState,
) {
    composable(CreateTransaction.routeName) {
        ScreenCreateTransaction(appState = state)
    }
}

@Composable
internal fun ScreenCreateTransaction(
    appState: ApplicationState,
) = UIWrapper<CreateTransactionViewModel>(appState = appState) {
    val state by uiState.collectAsState()

    with(appState) {
        hideTopAppBar()

        setupBottomSheet {
            when (state.bottomSheetType) {
                CATEGORY -> {
                    BottomSheetAddBudgetCategory(
                        onDismiss = { hideBottomSheet() },
                        onSearch = {

                        },
                        popularBudgetCategories = listOf(
                            ItemBudgetCategory(
                                title = "Makanan & Minuman",
                                icon = R.drawable.icon_food,
                            ),
                            ItemBudgetCategory(
                                title = "Kebutuhan Rumah",
                                icon = R.drawable.icon_house
                            ),
                            ItemBudgetCategory(
                                title = "Tagihan & Utilitas",
                                icon = R.drawable.icon_bill
                            )
                        ),
                        budgetCategories = listOf(
                            BudgetCategory(
                                title = "Kebutuhan sehari-hari",
                                categories = listOf(
                                    ItemBudgetCategory(
                                        title = "Makanan & Minuman",
                                        icon = R.drawable.icon_food,
                                        subCategories = listOf(
                                            ItemBudgetSubCategory(
                                                title = "Restoran",
                                                icon = R.drawable.icon_restaurant
                                            ),
                                            ItemBudgetSubCategory(
                                                title = "Food Delivery",
                                                icon = R.drawable.icon_food_delivery
                                            ),
                                            ItemBudgetSubCategory(
                                                title = "Coffee Shop",
                                                icon = R.drawable.icon_coffee
                                            ),
                                            ItemBudgetSubCategory(
                                                title = "Fast Food",
                                                icon = R.drawable.icon_fast_food
                                            ),
                                        )
                                    ),
                                    ItemBudgetCategory(
                                        title = "Bensin",
                                        icon = R.drawable.icon_bus
                                    )
                                )
                            ),
                            BudgetCategory(
                                title = "Kebutuhan rumah tangga",
                                categories = listOf(
                                    ItemBudgetCategory(
                                        title = "Kebutuhan Rumah",
                                        icon = R.drawable.icon_house
                                    ),
                                    ItemBudgetCategory(
                                        title = "Tagihan & Utilitas",
                                        icon = R.drawable.icon_bill
                                    )
                                )
                            ),
                        ),
                        onCategorySelected = {
                            showSnackbar(it)
                            hideBottomSheet()
                        },
                    )
                }
                DATE_PICKER -> {
                    BottomSheetDatePicker(
                        onSubmit = {
                            hideBottomSheet()
                        }
                    )
                }
            }

        }
    }
    BackHandler {
        if (state.step == 1) {
            //show confirmation
        } else {
            dispatch(CreateTransactionEvent.PrevPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue800)
    ) {
        when (state.step) {
            1 -> ScreenInputAmount(
                onClear = {},
                onRemove = {},
                onChange = {},
                onSubmit = {
                    dispatch(CreateTransactionEvent.NexPage)
                }
            )

            2 -> ScreenInputTransactionType(
                selected = "",
                onSelectedType = {
                    dispatch(CreateTransactionEvent.NexPage)
                }
            )

            3 -> ScreenInputAccount(
                transactionType = "",
                selectedAccount = "",
                onSelectedAccount = {
                    dispatch(CreateTransactionEvent.NexPage)
                }
            )

            4 -> ScreenInputTransactionNameAndCategory(
                transactionName = "",
                onChange = {},
                onSelectCategory = {
                    dispatch(CreateTransactionEvent.ChangeBottomSheet(CATEGORY))
                    showBottomSheet()
                },
            )

            5 -> ScreenInputDateTransaction(
                date = null,
                onSelectDate = {
                    dispatch(CreateTransactionEvent.ChangeBottomSheet(DATE_PICKER))
                    showBottomSheet()
                },
                onAddMore = {},
                onSave = {
                    dispatch(CreateTransactionEvent.NexPage)
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
                onSubmit = {

                }
            )

            else -> ScreenInputAmount()
        }

        if (state.step in 1..5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.clickable {
                        if (state.step > 1) {
                            dispatch(CreateTransactionEvent.PrevPage)
                        }
                    }
                )
                LinearProgressIndicator(
                    progress = state.percentage,
                    modifier = Modifier
                        .fillMaxWidth(
                            fraction = 0.7f
                        )
                        .height(8.dp),
                    color = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.onPrimary.copy(
                        alpha = 0.4f
                    ),
                    strokeCap = StrokeCap.Round
                )
                Text(
                    text = "${state.step}/5",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onPrimary
                )
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenCreateTransaction() {
    BaseMainApp {
        ScreenCreateTransaction(it)
    }
}