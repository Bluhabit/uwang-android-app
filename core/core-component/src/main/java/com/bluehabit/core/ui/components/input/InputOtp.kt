/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.theme.GaweanTheme
import java.lang.IllegalArgumentException

@Composable
fun InputOtp(
    modifier: Modifier = Modifier,
    otp: String = String.Empty,
    length: Int = 4,
    onChange: (String, Boolean) -> Unit = { _, _ -> }
) {
    LaunchedEffect(key1 = otp, block = {
        if (otp.length > length) {
            throw IllegalArgumentException(
                "Otp text value must not have more than otpLength: $length characters"
            )
        }
    })
    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(
            otp,
            selection = TextRange(otp.length)
        ),
        onValueChange = {
            if (it.text.length <= length) {
                onChange(it.text, it.text.length == length)
            }
        },
        keyboardActions = KeyboardActions(
            onDone = {

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
                    CharView(index = it, text = otp)
                    Spacer(modifier = Modifier.width(8.dp))
                }

            }
        }
    )
}

@Composable
fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .border(
                width = 1.dp,
                color = when {
                    isFocused -> Color.Green
                    else -> Color.LightGray
                },
                shape = RoundedCornerShape(8.dp)
            ),
        text = char,
        style = MaterialTheme.typography.h4,
        color = if (isFocused) Color.Green else Color.LightGray,
        textAlign = TextAlign.Center
    )
}

@Preview
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
        }
    }
}