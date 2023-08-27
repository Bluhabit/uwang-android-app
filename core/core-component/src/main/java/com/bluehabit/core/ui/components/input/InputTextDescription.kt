/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.*

@Composable
fun InputTextArea(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    label: String = "",
    enabled: Boolean = true,
    error: Boolean = false,
    errorMessage: String = String.Empty
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.W500,
            modifier = modifier,
            color = Gray700
        )
        OutlinedTextField(
            isError = error,
            enabled = enabled,
            value = value,
            onValueChange = onChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Gray300,
                textColor = Gray900,
                focusedBorderColor = Primary300,
                disabledBorderColor = Gray300,
                errorBorderColor = Error600
            ),
            modifier = Modifier
                .width(320.dp)
                .height(154.dp)
                .fillMaxWidth()
                .heightIn(min = 200.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W400
                )
                Spacer(modifier = modifier.height(6.dp))
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.W400,
                    modifier = modifier,
                    color = Error300
                )
            },

            )

    }
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
                placeholder = stringResource(id = R.string.text_placeholder_input_description),
                onChange = { input = it },
                label = stringResource(id = R.string.text_label_input_description),
                modifier = Modifier.size(
                    width = 320.dp,
                    height = 25.dp
                ),
                enabled = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputTextArea(
                value = input,
                placeholder = stringResource(id = R.string.text_placeholder_input_description),
                onChange = { input = it },
                label = stringResource(id = R.string.text_label_input_description),
                modifier = Modifier.size(
                    width = 320.dp,
                    height = 25.dp
                ),
                error = true
            )
        }

    }
}