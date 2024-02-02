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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.alert.AlertTextFieldError
import com.bluhabit.core.ui.components.alert.AlertTextFieldSuccess
import com.bluhabit.core.ui.components.alert.AlertTextFieldWithHint
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun TextFieldPrimary(
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

@Composable
fun TextFieldPrimary(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    state: TextFieldState = TextFieldState.None,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onAction: (FocusManager) -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = UwangTypography.BodySmall.Regular,
            color = UwangColors.Palette.Neutral.Grey9
        )
        TextFieldPrimary(
            modifier = modifier,
            label = {
                Text(
                    text = placeholder,
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Palette.Neutral.Grey7
                )
            },
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly,
            maxLines = maxLines,
            value = value,
            onValueChange = onValueChange,
            isError = state is TextFieldState.Error,
            trailingIcon = {
                when (state) {
                    is TextFieldState.Error -> {
                        Image(
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "",
                            modifier = Modifier
                                .size(dimens.dp_16)
                        )
                    }

                    TextFieldState.None -> Unit
                    is TextFieldState.Success -> Unit
                    is TextFieldState.WithHint -> Unit
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            onAction = onAction
        )
        when (state) {
            is TextFieldState.Error -> AlertTextFieldError(state.errorText)
            TextFieldState.None -> Unit
            is TextFieldState.Success -> {
                AlertTextFieldSuccess(state.successText)
            }

            is TextFieldState.WithHint -> {
                AlertTextFieldWithHint(state.hintText)
            }
        }
    }
}

@Composable
fun TextFieldPasswordPrimary(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    onChangeVisibility: (Boolean) -> Unit = {},
    enabled: Boolean = true,
    showPassword: Boolean = false,
    state: TextFieldState = TextFieldState.None,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onAction: (FocusManager) -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = UwangTypography.BodySmall.Regular,
            color = UwangColors.Palette.Neutral.Grey9
        )
        TextFieldPrimary(
            modifier = modifier,
            label = {
                Text(
                    text = placeholder,
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Palette.Neutral.Grey7
                )
            },
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly,
            maxLines = maxLines,
            value = value,
            onValueChange = onValueChange,
            isError = state is TextFieldState.Error,
            trailingIcon = {
                IconButton(onClick = {
                    onChangeVisibility(showPassword)
                }) {
                    Icon(
                        painter = painterResource(id = if (showPassword) R.drawable.eye_open else R.drawable.eye_close),
                        contentDescription = "",
                        modifier = Modifier
                            .size(dimens.dp_16),
                        tint = if (state is TextFieldState.Error) UwangColors.State.Error.Main else UwangColors.State.Neutral.Main
                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = keyboardOptions,
            onAction = onAction
        )
        when (state) {
            is TextFieldState.Error -> AlertTextFieldError(state.errorText)
            TextFieldState.None -> Unit
            is TextFieldState.Success -> {
                AlertTextFieldSuccess(state.successText)
            }

            is TextFieldState.WithHint -> {
                AlertTextFieldWithHint(state.hintText)
            }
        }
    }
}


@Preview
@Composable
fun PreviewTextField() {
    UwangTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            TextFieldPrimary(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.reset_password_input_label_email),
                placeholder = stringResource(id = R.string.reset_password_input_placeholder_email),
                value = "triandamai@gmail.com",
                onValueChange = {},
                state = TextFieldState.Success("Sukses"),
            )
            TextFieldPrimary(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(id = R.string.reset_password_input_label_email),
                placeholder = stringResource(id = R.string.reset_password_input_placeholder_email),
                value = "triandamai@gmail.com",
                onValueChange = {},
                state = TextFieldState.None,
            )
        }
    }
}