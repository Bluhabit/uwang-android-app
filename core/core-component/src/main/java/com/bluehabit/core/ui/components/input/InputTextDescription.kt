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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Error300
import com.bluehabit.core.ui.theme.Error600
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary300

@Composable
fun InputTextDescription(
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
            InputTextDescription(
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
            InputTextDescription(
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