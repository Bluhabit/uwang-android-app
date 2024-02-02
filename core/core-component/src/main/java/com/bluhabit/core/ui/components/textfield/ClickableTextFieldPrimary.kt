/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.components.alert.AlertTextFieldError
import com.bluhabit.core.ui.components.alert.AlertTextFieldSuccess
import com.bluhabit.core.ui.components.alert.AlertTextFieldWithHint
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun ClickableTextFieldPrimary(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
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
    state: TextFieldState = TextFieldState.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = UwangTypography.BodySmall.Regular,
            color = UwangColors.Palette.Neutral.Grey9
        )
        OutlinedTextField(
            modifier = modifier
                .clip(shape)
                .clickable {
                    onClick()
                },
            placeholder = {
                Text(
                    text = placeholder,
                    style = UwangTypography.BodySmall.Regular,
                    color = UwangColors.Palette.Neutral.Grey7
                )
            },
            shape = shape,
            colors = colors,
            value = value,
            onValueChange = {},
            enabled = true,
            textStyle = textStyle,
            isError = state is TextFieldState.Error,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            singleLine = true,
            maxLines = 1,
            readOnly = true,
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

