/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.bottomSheet.BaseBottomSheet
import com.bluehabit.budgetku.android.components.input.FormInput
import com.bluehabit.budgetku.android.ui.Grey300

@Composable
fun BottomSheetCalculateEmergencyFund() {
    BaseBottomSheet {
        Text(
            text = "Hitung Dana Darurat",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(10.dp))
        FormInput(
            label = "Pengeluaran bulananmu",
            placeholder = "0",
            trailingIcon = {
                Text(text = "Rp")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Status Pernikahan")
        ItemButtonGroup(
            labels = listOf(
                "Lajang",
                "Menikah"
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormInput(
            label = "Jumlah Tanggungan",
            placeholder = "0"
        )
        ItemButtonGroup(
            labels = listOf(
                "1",
                "2",
                "3"
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.small
                )
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.small,
                    color = Grey300
                )
                .padding(
                    all = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dana darurat diperlukan",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "Rp0",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )

        }

    }
}

@Preview
@Composable
fun PreviewBottomSheetCalculateEmergencyFund() {
    BaseMainApp {
        BottomSheetCalculateEmergencyFund()
    }
}