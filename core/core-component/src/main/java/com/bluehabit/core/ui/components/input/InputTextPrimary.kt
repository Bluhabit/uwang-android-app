/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Error500
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
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    label: String = "",
    enabled: Boolean = true,
    error: Boolean = false,
    errorMessage:String=String.Empty
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle2,
            fontWeight=FontWeight.W500,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(
            isError = error,
            enabled = enabled,
            value = value,
            onValueChange = onChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Gray300,
                textColor = Gray500,
                focusedBorderColor = Primary300,
                disabledBorderColor = Gray300,
                errorBorderColor = Rose700
            ),
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 40.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W400
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.W400
            ),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = if (error) {
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = "",
                        tint = Error500
                    )
                }
            } else null
        )
        Spacer(modifier = modifier.height(6.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle2,
            fontWeight=FontWeight.W400,
            modifier = modifier,
            color = Error500
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
                placeholder = "Masukkan email disini",
                onChange = { input = it },
                error = true
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                placeholder = "Masukkan email disini",
                onChange = { input = it },
                error = false
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                placeholder = "Masukkan email disini",
                onChange = { input = it },
                enabled = true
            )
            InputTextPrimary(
                label = "Email",
                value = input,
                placeholder = "Masukkan email disini",
                onChange = { input = it },
                enabled = false
            )
        }
    }
}