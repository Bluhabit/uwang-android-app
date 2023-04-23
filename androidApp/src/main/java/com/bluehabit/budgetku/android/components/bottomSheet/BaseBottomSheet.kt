/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components.bottomSheet

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.bluehabit.budgetku.android.ui.Grey300

@Composable
fun BaseBottomSheet(
    textConfirmation: String = "Confirm",
    enableConfirmation: Boolean = true,
    showButtonConfirmation:Boolean=true,
    showLineHeader: Boolean = true,
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
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (
            showLineHeader
        ) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(6.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Grey300)
            ) {

            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
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
            if(showButtonConfirmation) {
                ButtonPrimary(
                    text = textConfirmation,
                    enabled = enableConfirmation,
                    fullWidth = true,
                    onClick = onConfirm
                )
            }
        }
    }

}


@Preview
@Composable
fun PreviewBaseBottomSheet() {
    BudgetKuTheme {
        BaseBottomSheet()
    }
}