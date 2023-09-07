/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme

@Composable
fun InputTextArea(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    enabled: Boolean = true,
    error: Boolean = false,
    minLines: Int = 4,
) {
    BaseInputTextPrimary(
        modifier = modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
        },
        error = error,
        value = value,
        minLines = minLines,
        onChange = { newValue ->
            onChange(newValue)
        },
        enabled = enabled
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputTextDescription() {
    GaweanTheme()
    {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 40.dp
                )
                .fillMaxWidth()
                .heightIn(min = 215.dp),
        ) {
            var input by remember {
                mutableStateOf("")
            }
            InputTextArea(
                value = input,
                placeholder = stringResource(id = R.string.text_placeholder_input_description_create_task),
                onChange = { input = it },
                modifier = Modifier.size(
                    width = 320.dp,
                    height = 25.dp
                ),
                enabled = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputTextArea(
                value = input,
                placeholder = stringResource(id = R.string.text_placeholder_input_description_create_task),
                onChange = { input = it },
                modifier = Modifier.size(
                    width = 320.dp,
                    height = 25.dp
                ),
                error = true
            )
        }

    }
}