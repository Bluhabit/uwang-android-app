/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.components.BaseBottomSheet
import com.bluehabit.budgetku.android.components.NumPad

@Composable
fun EditTransactionNumpad(
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    BaseBottomSheet(
        enableConfirmation = false,
        showButtonConfirmation = false,
        onDismiss = onDismiss,
    ) {
        Text(
            text = stringResource(id = R.string.text_input_value_transaction),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "1.000.000",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            NumPad(onSubmit = onConfirm)
        }
    }
}