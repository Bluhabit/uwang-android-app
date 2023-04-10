/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
fun FormInputPassword(
    value: String = "",
    label: String = "",
    placeholder: String = "",
    error: Boolean = false,
    errorMessage: String = "",
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit = {}
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFFAFAFA),
                unfocusedBorderColor = Color(0xFFFAFAFA),
                focusedBorderColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onSurface,
                disabledTextColor = MaterialTheme.colors.onSurface
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )
            },
            isError = error,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
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
        if (error) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.error
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewFormInputPassword() {
    BaseMainApp {
        FormInputPassword()
    }
}