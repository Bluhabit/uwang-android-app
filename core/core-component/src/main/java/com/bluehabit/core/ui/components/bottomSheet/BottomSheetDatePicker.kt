/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey400
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun BottomSheetDatePicker(
    selectedDate: LocalDate? = null,
    onSelectDay: (LocalDate) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    BaseBottomSheet(
        onDismiss = {},
        onConfirm = onSubmit,
        textConfirmation = "Simpan"
    ) {
        StaticCalendar(
            modifier = Modifier.fillMaxHeight(fraction = 0.5f),
            horizontalSwipeEnabled = true,
            daysOfWeekHeader = {
                Row(
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                ) {
                    it.forEach { dayOfWeek ->
                        Text(
                            textAlign = TextAlign.Center,
                            text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight(),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            },
            monthHeader = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        it.currentMonth = it.currentMonth.minusMonths(1)
                    }) {
                        Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
                    }
                    Text(
                        text = it.currentMonth.month
                            .getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .lowercase()
                            .replaceFirstChar { it.titlecase() }
                            .plus(", ${it.currentMonth.year}"),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { it.currentMonth = it.currentMonth.plusMonths(1) }) {
                        Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "")
                    }
                }
            },
            dayContent = {
                Column(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .clickable(
                            enabled = true,
                            onClick = {
                                onSelectDay(it.date)
                            }
                        )
                        .background(
                            when {
                                (it.date == LocalDate.now()) -> MaterialTheme.colors.primary.copy(
                                    alpha = 0.7f
                                )

                                it.date == selectedDate -> MaterialTheme.colors.primary
                                else -> Color.Transparent
                            }
                        )
                        .padding(
                            all = 10.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it.date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = when {
                            it.isCurrentDay -> MaterialTheme.colors.onPrimary
                            it.isFromCurrentMonth -> MaterialTheme.colors.onSurface
                            else -> Grey400
                        }
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewBottomSheetDatePicker() {
    BaseMainApp {
        BottomSheetDatePicker()
    }
}