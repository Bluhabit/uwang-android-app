/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Error500
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray500
import com.bluehabit.core.ui.theme.Primary300
import com.bluehabit.core.ui.theme.Rose700

@Composable
fun BaseInputTextPrimary(
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    error: Boolean = false,
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    value: String = String.Empty,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onFocusChanged: (FocusState) -> Unit = {},
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        readOnly = readOnly,
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
            .defaultMinSize(minHeight = 40.dp)
            .onFocusChanged {
                onFocusChanged(it)
            },
        placeholder = placeholder,
        singleLine = false,
        textStyle = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.W400
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        leadingIcon =
        if (leadingIcon != null) {
            { leadingIcon() }
        } else {
            null
        },
        trailingIcon = if (error) {
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = "",
                    tint = Error500
                )
            }
        } else {
            if (trailingIcon != null) {
                { trailingIcon() }
            } else {
                null
            }
        }
    )
}