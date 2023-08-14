/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Primary300
import com.bluehabit.core.ui.theme.Rose700


/**
 * Button Primary
 * @param modifier
 *
 * @sample PreviewInputTextPrimary
 * */

@Composable
fun InputTextPrimary(
    value: String = "",
    onChange: (String) -> Unit = {},
    label: String = "",
    enable: Boolean = true,
    eror: Boolean = false
) {
    Column() {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle2
        )
        OutlinedTextField(
            isError = eror,
            enabled = enable,
            value = value, onValueChange = onChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Gray300,
                textColor = Gray500,
                focusedBorderColor = Primary300,
                disabledBorderColor = Gray300,
                errorBorderColor = Rose700
            )
        )

    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputTextPrimary() {
    GaweanTheme() {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
        ) {
            var input by remember {
                mutableStateOf("")
            }
            InputTextPrimary(
                label = "Email",
                value = input,
                onChange = { input = it },
                eror = true
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                onChange = { input = it },
                eror = false
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                onChange = { input = it },
                enable = true
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                onChange = { input = it },
                enable = false
            )
        }
    }
}