/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.hideBottomSheet
import com.bluehabit.budgetku.android.components.BottomSheetDatePicker
import com.bluehabit.budgetku.android.components.BottomSheetSelectAccount
import com.bluehabit.budgetku.android.components.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.ItemBottomSheetAccount
import com.bluehabit.budgetku.android.feature.editTransaction.components.EditTransactionNumpad

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
fun ScreenEditTransaction(
    appState: ApplicationState,
) = UIWrapper<EditTransactionViewModel>(appState) {
    val state by uiState.collectAsState()
    with(appState) {
        hideBottomSheet()
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
            when(state.bottomSheetType) {
                EditTransactionBottomSheetType.VALUE -> EditTransactionNumpad(
                    onConfirm = { hideBottomSheet() },
                    onDismiss = { hideBottomSheet() },
                )
                EditTransactionBottomSheetType.NOTE -> {} // Edit text? another bottom sheet?
                EditTransactionBottomSheetType.CATEGORY -> {} // waiting for feature/BKA-130
                EditTransactionBottomSheetType.ACCOUNT -> BottomSheetSelectAccount(
                    accounts = listOf(
                        ItemBottomSheetAccount(
                            icon = R.drawable.ic_account_bca,
                            name = stringResource(id = R.string.text_bank_bca),
                            value = "Rp.1000.000",
                            isSelected = true,
                        ),
                        ItemBottomSheetAccount(
                            icon = R.drawable.ic_account_bca,
                            name = stringResource(id = R.string.text_bank_bca),
                            value = "Rp.1000.000",
                            isSelected = false,
                        ),
                        ItemBottomSheetAccount(
                            icon = R.drawable.ic_account_jago,
                            name = stringResource(id = R.string.text_bank_jago),
                            value = "Rp.400.000",
                            isSelected = false,
                        ),
                    ),
                    onSelected = { hideBottomSheet() }
                )
                EditTransactionBottomSheetType.DATE -> BottomSheetDatePicker(onSubmit = { hideBottomSheet() })
            }
        }
    }
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1962FE))
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(bottom = 16.dp),
            ) {
                EditTransactionTop(
                    onCredit = {  },
                    onDebit = {  },
                )
                Spacer(modifier = Modifier.height(24.dp))
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(25f, 25f), 0f)
                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)) {
                    drawLine(
                        color = Color.Gray, // using 0xFFe0e0e0 became almost not visible
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        pathEffect = pathEffect
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                EditTransactionDetail(
                    onEditValue = { dispatch(EditTransactionEvent.EditValue) },
                    onEditCategory = { dispatch(EditTransactionEvent.EditCategory) },
                    onEditNote = { dispatch(EditTransactionEvent.EditNote) },
                    onEditAccount = { dispatch(EditTransactionEvent.EditAccount) },
                    onEditDate = { dispatch(EditTransactionEvent.EditDate) },
                )
            }
        }
        EditTransactionBottom(
            onSave = {  }
        )
    }
}

@Composable
fun EditTransactionTop(
    onCredit: () -> Unit = {},
    onDebit: () -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_title_edit_transaction),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        BorderStroke(1.5.dp, Color(0xFFe0e0e0)),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        onCredit()
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_up_circle),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.text_credit_edit_transaction),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onDebit()
                    }
                    .border(
                        BorderStroke(1.5.dp, Color(0xFFe0e0e0)),
                        RoundedCornerShape(16.dp)
                    )
                    .background(Color(0xFF1962FE))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_down_circle),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.text_debit_edit_transaction),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun EditTransactionDetail(
    onEditValue: () -> Unit = {},
    onEditCategory: () -> Unit = {},
    onEditNote: () -> Unit = {},
    onEditAccount: () -> Unit = {},
    onEditDate: () -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.text_detail_edit_transaction),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        ItemEditTransactionDetail(
            icon = R.drawable.ic_rp,
            value = "50.000",
            onClick = onEditValue
        )
        ItemEditTransactionDetail(
            icon = R.drawable.ic_food,
            value = "Makanan & Minuman",
            isExpandable = true,
            onClick = onEditCategory,
        )
        ItemEditTransactionDetail(
            icon = R.drawable.ic_pencil,
            value = "Jajan Gofood",
            onClick = onEditNote,
        )
        ItemEditTransactionDetail(
            icon = R.drawable.ic_bca,
            value = stringResource(id = R.string.text_bank_bca),
            isExpandable = true,
            onClick = onEditAccount,
        )
        ItemEditTransactionDetail(
            icon = R.drawable.ic_calendar,
            value = "8 April 2023",
            isExpandable = true,
            onClick = onEditDate,
        )
    }
}

@Composable
fun ItemEditTransactionDetail(
    icon: Int = 0,
    value: String = "",
    isExpandable: Boolean = false,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(Color(0xFFfafafa))
            .border(
                BorderStroke(1.5.dp, Color(0xFFe0e0e0)),
                RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.weight(1f),
            text = value,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium
        )
        if (isExpandable) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_short_down),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun EditTransactionBottom(
    onSave: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        ButtonOutlinedPrimary(
            text = stringResource(id = R.string.text_save_edit_transaction),
            onClick = onSave
        )
    }
}