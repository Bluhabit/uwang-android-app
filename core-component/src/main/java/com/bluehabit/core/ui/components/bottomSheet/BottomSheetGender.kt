/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.BudgetKuTheme

enum class Gender(val value: String, val label: Int) {
    MALE(value = "L", label = R.string.male),
    FEMALE(value = "P", label = R.string.female)
}


@Composable
fun BottomSheetGenderPicker(
    title: String = "",
    genders: List<Gender> = listOf(Gender.MALE, Gender.FEMALE),
    selectedGender: Gender? = null,
    onDismiss: () -> Unit = {},
    onConfirm: (Gender) -> Unit = {}
) {
    var selected by remember {
        mutableStateOf(selectedGender)
    }
    BaseBottomSheet(
        onDismiss = onDismiss,
        onConfirm = {
            selected?.let {
                onConfirm(it)
            }
        },
        textConfirmation = stringResource(R.string.text_button_confirmation_gender_bottom_sheet),
        enableConfirmation = selected != null
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(20.dp))
        genders.forEachIndexed { index, gender ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        45.dp
                    )
                    .clickable {
                        selected = gender
                    }
                    .padding(
                        vertical = 10.dp
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = gender.label),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Normal,
                    color = if (selected?.value == gender.value) MaterialTheme.colors.onSurface else Color.LightGray
                )
                if (selected?.value == gender.value) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = "",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            if (index != genders.size - 1) {
                Divider()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview
@Composable
fun PreviewBottomSheetGenderPicker() {
    BudgetKuTheme {
        BottomSheetGenderPicker()
    }
}