/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.BudgetKuTheme

@Composable
fun BaseBottomSheet(
    textConfirmation: String = "Confirm",
    enableConfirmation: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        modifier = Modifier
            .defaultMinSize(
                minHeight = 100.dp
            )
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                )
            )
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            imageVector = Icons.Outlined.Clear,
            contentDescription = "",
            modifier = Modifier.clickable(
                enabled = true,
                onClick = onDismiss
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        content(this)
        Spacer(modifier = Modifier.height(16.dp))
        ButtonPrimary(
            text = textConfirmation,
            enabled = enableConfirmation,
            fullWidth = true,
            onClick = onConfirm
        )
    }

}


@Preview
@Composable
fun PreviewBaseBottomSheet() {
    BudgetKuTheme {
        BaseBottomSheet()
    }
}