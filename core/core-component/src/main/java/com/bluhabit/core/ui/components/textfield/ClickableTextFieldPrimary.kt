/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.alert.AlertTextFieldError
import com.bluhabit.core.ui.components.alert.AlertTextFieldSuccess
import com.bluhabit.core.ui.components.alert.AlertTextFieldWithHint
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun ClickableTextFieldPrimary(
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = UwangColors.Text.Main,
        backgroundColor = Color.White,
        focusedBorderColor = UwangColors.State.Primary.Main,
        unfocusedBorderColor = UwangColors.Text.Border,
        focusedLabelColor = UwangColors.Palette.Neutral.Grey7,
        unfocusedLabelColor = UwangColors.Palette.Neutral.Grey7,
        errorBorderColor = UwangColors.State.Error.Main,
        cursorColor = UwangColors.Text.Main,
        errorCursorColor = UwangColors.Text.Main
    ),
    textStyle: TextStyle = UwangTypography.BodySmall.Regular,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    onAction: (FocusManager) -> Unit = {}
) {
    val focus = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier,
        placeholder = label,
        shape = shape,
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        textStyle = textStyle,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                onAction(focus)
            },
            onGo = {
                onAction(focus)
            },
            onNext = {
                onAction(focus)
            },
            onPrevious = {
                onAction(focus)
            },
            onSend = {
                onAction(focus)
            },
            onSearch = {
                onAction(focus)
            },
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        readOnly = readOnly,
    )
}

