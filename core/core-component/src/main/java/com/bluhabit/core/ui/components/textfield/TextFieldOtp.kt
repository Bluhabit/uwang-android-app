/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun TextFieldOtp(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    length: Int = 4,
    enabled: Boolean = true,
    error: Boolean = false,
    onChange: (String) -> Unit = {},
    onDone: () -> Unit = {}
) {
    BasicTextField(
        enabled = enabled,
        modifier = modifier,
        value = TextFieldValue(
            text = value,
            selection = TextRange(length)
        ),
        onValueChange = { newTextFieldValue ->
            val newText = newTextFieldValue.text
            if (newText.all { it.isDigit() }) {
                if (newText.length <= length) {
                    onChange(newText)
                    if (newText.length == length) {
                        onDone()
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                repeat(length) {
                    CharView(
                        index = it,
                        text = value,
                        enabled = enabled,
                        error = error,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }
        }
    )
}

@Composable
fun TextFieldOtp(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    length: Int = 4,
    enabled: Boolean = true,
    state: TextFieldState = TextFieldState.None,
    onChange: (String) -> Unit = {},
    onDone: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier,
        ) {
            TextFieldOtp(
                modifier = modifier.align(Alignment.Center),
                enabled = enabled,
                length = length,
                value = value,
                error = state is TextFieldState.Error,
                onDone = onDone,
                onChange = onChange
            )
        }
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        if (state is TextFieldState.Error) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.alert_triangle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_16)
                )
                Spacer(modifier = Modifier.padding(end = dimens.dp_8))
                Text(
                    text = state.errorText,
                    style = UwangTypography.LabelMedium.Regular,
                    color = UwangColors.State.Error.Main
                )
            }
        }
    }
}

@Composable
fun CharView(
    index: Int,
    text: String,
    enabled: Boolean = true,
    error: Boolean = false,
) {
    val char = when {
        index == text.length -> "0"
        index > text.length -> "0"
        else -> text[index].toString()
    }
    Box(
        modifier = Modifier
            .width(48.dp)
            .height(46.dp)
            .border(
                width = 1.dp,
                color = when {
                    (index == text.length && enabled) -> UwangColors.State.Primary.Main
                    error -> UwangColors.State.Error.Main
                    else -> UwangColors.Text.Border
                },
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = if (enabled) UwangColors.Base.White else UwangColors.Palette.Neutral.Grey2,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = char,
            style = UwangTypography.DisplayXS.Medium,
            color = UwangColors.Text.Main,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun TextFieldOtpPreview() {
    UwangTheme {
        val focusManager = LocalFocusManager.current
        var otpState by remember {
            mutableStateOf("")
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            TextFieldOtp(
                enabled = false,
                length = 4,
                error = false
            )
            TextFieldOtp(
                enabled = true,
                length = 4,
                value = otpState,
                onDone = {
                    focusManager.clearFocus(true)
                },
                onChange = { value ->
                    otpState = value
                },
                error = false
            )
            TextFieldOtp(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                length = 4,
                value = otpState,
                state = TextFieldState.None,
                onDone = {
                    focusManager.clearFocus(true)
                },
                onChange = { value ->
                    otpState = value
                }
            )
        }
    }
}