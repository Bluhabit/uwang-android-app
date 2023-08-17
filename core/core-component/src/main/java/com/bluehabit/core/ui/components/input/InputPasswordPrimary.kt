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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Primary300
import com.bluehabit.core.ui.theme.Rose700


/**
 * Input Password Primary
 * @param modifier
 *
 * @sample PreviewInputPasswordPrimary
 * */

@Composable
fun InputPasswordPrimary(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    label: String = "",
    enabled: Boolean = true,
    error: Boolean = false
) {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Gray300,
                textColor = Gray500,
                focusedBorderColor = Primary300,
                disabledBorderColor = Gray300,
                errorBorderColor = Rose700
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )
            },
            isError = error,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (visible) R.drawable.eye_open else R.drawable.eye_close),
                    contentDescription = "",
                    tint = if (visible) MaterialTheme.colors.primary
                    else MaterialTheme.colors.onSurface,
                    modifier = Modifier.clickable {
                        visible = !visible
                    }
                )
            },
            textStyle = MaterialTheme.typography.subtitle2,
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputPasswordPrimary() {
    GaweanTheme {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
        ) {
            var visible by remember {
                mutableStateOf("")
            }
            InputPasswordPrimary(
                label = "Password",
                value = visible,
                onChange = { visible = it },
            )
            InputPasswordPrimary(
                label = "Password",
                value = visible,
                onChange = { visible = it },
            )
        }
    }
}