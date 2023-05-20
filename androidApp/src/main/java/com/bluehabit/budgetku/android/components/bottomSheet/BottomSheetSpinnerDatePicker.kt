/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components.bottomSheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.ui.BudgetKuTheme
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate

@Composable
fun BottomSheetSpinnerDatePicker(
    title: String = "",
    textButtonConfirmation:String="",
    selectedDate: LocalDate? = null,
    onDismiss: () -> Unit = {},
    onConfirm: (LocalDate) -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density

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
        textConfirmation = textButtonConfirmation,
        enableConfirmation = selected != null
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            WheelDatePicker(
                modifier=Modifier.fillMaxWidth(),
                startDate = selected ?: LocalDate.now(),
                onSnappedDate = {
                    selected = it
                },
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = true,
                    color = Color.Transparent,
                    border = BorderStroke(
                        width=0.dp,
                        color = Color.Transparent
                    )
                ),
                size = DpSize(
                    width = currentWidth,
                    height = currentWidth/2
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetSpinnerDatePicker() {
    BudgetKuTheme {
        BottomSheetSpinnerDatePicker()
    }
}