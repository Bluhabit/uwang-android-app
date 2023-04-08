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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
fun FormInputWithIcon(
    value: String = "",
    label: String = "Nama Lengkap",
    placeholder: String = "Masukkan nama lengkapmu",
    icon: @Composable (() -> Unit)? = null,
    clickable: Boolean = false,
    error:Boolean=false,
    errorMessage:String="",
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {
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
            readOnly = clickable,
            enabled=!clickable,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = clickable,
                    onClick = onClick
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFFAFAFA),
                unfocusedBorderColor = Color(0xFFFAFAFA),
                focusedBorderColor = MaterialTheme.colors.primary,
                textColor = MaterialTheme.colors.onSurface,
                disabledTextColor = MaterialTheme.colors.onSurface
            ),
            isError=error,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = icon,
            textStyle = MaterialTheme.typography.subtitle2
        )
        if(error){
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.error
            )
        }else{
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewFormInputWithIcon() {
    BaseMainApp {
        FormInputWithIcon()
    }
}