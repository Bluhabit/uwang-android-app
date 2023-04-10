/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey700

@Composable
fun BottomSheetAddBudgetTransaction(
    onDismiss: () -> Unit = {},
    onAddAccount: () -> Unit = {},
    onAddTransaction: () -> Unit = {},
    onAddTransfer: () -> Unit = {},
    onAddBudget: () -> Unit = {}
) {
    BaseBottomSheet(
        textConfirmation = "",
        enableConfirmation = true,
        showButtonConfirmation = false,
        onDismiss = onDismiss,
        onConfirm = {},
        content = {
            Text(
                text = stringResource(R.string.text_title_bottom_sheet_add_budget_transaction),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(25.dp))
            ItemAddBudgetTransaction(
                icon = R.drawable.ic_add_account,
                title = stringResource(R.string.title_item_add_account_budget),
                message = stringResource(R.string.subtitle_item_add_account_budget),
                onClick = onAddAccount
            )
            ItemAddBudgetTransaction(
                icon = R.drawable.ic_add_transaction,
                title = stringResource(R.string.title_item_add_transaction_budget),
                message = stringResource(R.string.subtitle_item_add_transaction_budget),
                onClick = onAddTransaction
            )
            ItemAddBudgetTransaction(
                icon = R.drawable.ic_add_transfer,
                title = stringResource(R.string.title_item_add_transfer_budget),
                message = stringResource(R.string.subtitle_item_add_transfer_budget),
                onClick = onAddTransfer
            )
            ItemAddBudgetTransaction(
                icon = R.drawable.ic_add_budget,
                title = stringResource(R.string.title_item_add_budget),
                message = stringResource(R.string.subtitle_item_add_budget),
                onClick = onAddBudget
            )
        }
    )
}

@Composable
fun ItemAddBudgetTransaction(
    title: String = "",
    message: String = "",
    icon: Int = 0,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = onClick
            ),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Medium,
                    color = Grey700,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Preview
@Composable
fun PreviewBottomSheetAddBudgetTransaction() {
    BaseMainApp {
        BottomSheetAddBudgetTransaction()
    }
}