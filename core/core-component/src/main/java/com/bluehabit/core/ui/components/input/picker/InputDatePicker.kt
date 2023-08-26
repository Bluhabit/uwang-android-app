/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.BaseInputTextPrimary
import com.bluehabit.core.ui.theme.Gray500

@Composable
fun InputDatePicker(
    modifier: Modifier = Modifier,
    error: Boolean = false,
    enabled: Boolean = true,
    placeholder: String = String.Empty,
    value: String = String.Empty,
    onChange: (String) -> Unit = {},
    onClick: () -> Unit = {},
) {
    BaseInputTextPrimary(
        modifier = modifier,
        enabled = enabled,
        readOnly = true,
        placeholder = placeholder,
        error = error,
        value = value,
        onChange = onChange,
        onFocusChanged = {
            if (it.isFocused) {
                onClick()
            }
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar_pick),
                tint = Gray500,
                contentDescription = "",
                modifier = modifier
                    .clickable {
                        onClick()
                    }
            )
        }
    )
}

@Preview
@Composable
fun InputDatePickerPreview() {
    val value = remember {
        mutableStateOf("")
    }

    fun clearOrFill() {
        if (value.value == "") {
            value.value = "Dimulai..."
        } else {
            value.value = ""
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        InputDatePicker(
            placeholder = "Ayo mulai",
            value = value.value,
            onChange = {
                value.value = it
            }
        ) {
            clearOrFill()
        }
    }
}
