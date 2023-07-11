/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.bluehabit.core.ui.theme.Grey500

@Composable
fun MultilineHintTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
) {
    BasicTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        textStyle = textStyle,
        singleLine = false,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        color = Grey500
                    )
                }
                innerTextField.invoke()
            }
        }
    )
}