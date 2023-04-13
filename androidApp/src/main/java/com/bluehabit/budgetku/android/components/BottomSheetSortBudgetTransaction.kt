/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey500

@Composable
fun BottomSheetSortBudgetTransaction(
    onDismiss: () -> Unit = {}
) {
    BaseBottomSheet(
        textConfirmation = "",
        enableConfirmation = true,
        showButtonConfirmation = false,
        onDismiss = onDismiss,
        onConfirm = {},
        content = {
            Text(
                text = stringResource(R.string.text_title_bottom_sheet_sort_budget_transaction),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(40.dp))
            ItemSortBudgetTransaction(
                title = stringResource(R.string.title_item_add_sort_pengeluaran_kecil)
            )
            ItemSortBudgetTransaction(
                title = stringResource(R.string.title_item_add_sort_pengeluaran_terbesar)
            )
            ItemSortBudgetTransaction(
                title = stringResource(R.string.title_item_add_sort_pengeluaran_terbaru)
            )
            ItemSortBudgetTransaction(
                title = stringResource(R.string.title_item_add_sort_pengeluaran_terlama)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimary(
                text = stringResource(R.string.text_button_choose_category_budget),
                onClick = {},
            )
        }
    )
}

@Composable
fun ItemSortBudgetTransaction(
    title: String = "",
) {
    Text(
        text = title,
        color = Grey500,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Normal
    )
    Spacer(modifier = Modifier.height(24.dp))
    Divider()
    Spacer(modifier = Modifier.height(24.dp))
}


@Preview
@Composable
fun PreviewBottomSheetSortBudgetTransaction() {
    BaseMainApp {
        BottomSheetSortBudgetTransaction()
    }
}