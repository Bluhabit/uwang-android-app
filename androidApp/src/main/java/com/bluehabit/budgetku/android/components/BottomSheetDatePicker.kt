/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate

@Composable
fun BottomSheetDatePicker(
    title: String = "Pilih Tanggal Lahir Kamu",
    selectedDate: LocalDate? = null,
    onDismiss: () -> Unit = {},
    onConfirm: (LocalDate) -> Unit = {}
) {
    var selected by remember {
        mutableStateOf(selectedDate)
    }
    BaseBottomSheet(
        onDismiss = onDismiss,
        onConfirm = {
            selected?.let {
                onConfirm(it)
            }
        },
        textConfirmation = "Pilih",
        enableConfirmation = selected != null
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        WheelDatePicker(
            modifier=Modifier.fillMaxWidth(),
            startDate = selected ?: LocalDate.now(),
            onSnappedDate = {
                selected = it
            },
            selectorProperties = WheelPickerDefaults.selectorProperties(
                enabled = true,
            )
        )
    }
}

@Preview
@Composable
fun PreviewBottomSheetDatePicker() {
    BudgetKuTheme {
        BottomSheetDatePicker()
    }
}