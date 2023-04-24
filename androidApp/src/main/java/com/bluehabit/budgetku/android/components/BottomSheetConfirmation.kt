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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.components.button.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey700

@Composable
fun BottomSheetConfirmation(
    title: String = "",
    message: String = "",
    textConfirmation: String = "Confirm",
    textCancel: String = "Confirm",
    enableConfirmation: Boolean = true,
    enableCancel: Boolean = true,
    showButtonConfirmation: Boolean = true,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val buttonWidth = (currentWidth / 2) - 28.dp
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
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(6.dp)
                .clip(
                    MaterialTheme.shapes.medium
                )
                .background(Grey300)
        ) {

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
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Normal,
                color = Grey700
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                ButtonOutlinedPrimary(
                    text = textCancel,
                    fullWidth = false,
                    modifier = Modifier.width(buttonWidth)
                )
                ButtonPrimary(
                    text = textConfirmation,
                    fullWidth = false,
                    modifier = Modifier.width(buttonWidth)
                )
            }
        }
    }

}


@Preview
@Composable
fun PreviewBottomSheetConfirmation() {
    BudgetKuTheme {
        BottomSheetConfirmation(
            title = "Yakin hapus transaski ini?",
            message = "Data transaksi yang telah kamu isi akan hilang dari catatan transaksi kamu",
            textConfirmation = "Hapus",
            textCancel = "Batal"
        )
    }
}