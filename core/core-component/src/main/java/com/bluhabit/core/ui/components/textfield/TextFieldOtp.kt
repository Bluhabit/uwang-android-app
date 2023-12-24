/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun TextFieldOtp(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    length: Int = 4,
    enabled: Boolean = true,
    onChange: (String) -> Unit = {},
    onDone: () -> Unit = {}
) {
    BasicTextField(
        enabled = enabled,
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.length <= length) {
                onChange(it)
                if (it.length == length) {
                    onDone()
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
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
                        enabled = enabled
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

            }
        }
    )
}

@Composable
fun CharView(
    index: Int,
    text: String,
    enabled: Boolean = true
) {
    Box(
        modifier = Modifier
            .width(47.dp)
            .height(44.dp)
            .border(
                width = 1.dp,
                color = when {
                    enabled -> CustomColor.Neutral.Grey13
                    else -> CustomColor.Neutral.Grey7
                },
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.TopCenter),
            text = if (index >= text.length) "0" else text[index].toString(),
            style = CustomTypography.Body.Large.W600,
            color = if (index >= text.length || !enabled) CustomColor.Neutral.Grey7 else CustomColor.Neutral.Grey13,
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
                }
            )
        }
    }
}