/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray300
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun InputOtp(
    modifier: Modifier = Modifier,
    otp: String = String.Empty,
    length: Int = 4,
    onChange: (String, Boolean) -> Unit = { _, _ -> },
    onDone: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })
    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        value = TextFieldValue(
            text = otp,
            selection = TextRange(otp.length)
        ),
        onValueChange = {
            if (it.text.length <= length) {
                onChange(it.text, it.text.length == length)
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(length) {
                    CharView(
                        index = it,
                        text = otp,
                        isFocused = isFocused
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

            }
        }
    )
}

@Composable
fun CharView(
    index: Int,
    text: String,
    isFocused: Boolean = false
) {
    val context = LocalContext.current
    val char = when {
        index == text.length -> "0"
        index > text.length -> "0"
        else -> text[index].toString()
    }
    Box(
        modifier = Modifier
            .size(64.dp.from(context = context))
            .border(
                width = 1.dp,
                color = when {
                    isFocused -> Gray900
                    else -> Gray300
                },
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = char,
            style = MaterialTheme.typography.h4,
            color = if (isFocused) Gray900 else Gray300,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputOtp() {
    GaweanTheme {
        Column(
            modifier = Modifier.padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
        ) {
            InputOtp(
                length = 4,
                otp = "23456"
            )
            Spacer(modifier = Modifier.height(10.dp))
            InputOtp(
                length = 4,
                otp = ""
            )
        }
    }
}