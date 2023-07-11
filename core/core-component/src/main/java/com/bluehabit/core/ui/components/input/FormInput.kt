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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp

@Composable
fun FormInput(
    value: String = "",
    label: String = "",
    placeholder: String = "",
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    error: Boolean = false,
    errorMessage: String = "",
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            isError = error,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFFAFAFA),
                unfocusedBorderColor = Color(0xFFFAFAFA),
                focusedBorderColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onSurface,
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )
            },
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = MaterialTheme.typography.subtitle2,
            singleLine = true
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Normal,
            color = if (error) MaterialTheme.colors.error else Color.Transparent
        )
    }
}

@Composable
fun FormInput(
    value: String = "",
    label: String = "",
    placeholder: String = "",
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    error: Boolean = false,
    errorMessage: String = "",
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            isError = error,
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .clickable(
                    enabled = true,
                    onClick = onClick
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFFAFAFA),
                unfocusedBorderColor = Color(0xFFFAFAFA),
                focusedBorderColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onSurface,
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )
            },
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = MaterialTheme.typography.subtitle2,
            singleLine = true
        )
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.Normal,
            color = if (error) MaterialTheme.colors.error else Color.Transparent
        )
    }
}

@Preview
@Composable
fun PreviewFormInput() {
    BaseMainApp {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp
            )
        ) {

            FormInput()
            FormInput(
                error = true,
                errorMessage = "Ini Error"
            )
        }
    }
}