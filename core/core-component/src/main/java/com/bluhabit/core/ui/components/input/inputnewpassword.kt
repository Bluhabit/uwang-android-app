/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.CustomColor.Neutral.Grey7
import com.bluhabit.core.ui.theme.Error500
import com.bluhabit.core.ui.theme.Gray300
import com.bluhabit.core.ui.theme.Gray500
import com.bluhabit.core.ui.theme.Rose700
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun InputPasswordPrimary(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    placeholder: String = String.Empty,
    onChange: (String) -> Unit = {},
    label: String = "",
    enabled: Boolean = true,
    error: Boolean = false,
    errorMessage: String = String.Empty
) {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(
            isError = error,
            enabled = enabled,
            value = value,
            onValueChange = onChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = Gray300,
                textColor = Gray500,
                focusedBorderColor = Grey7 ,
                disabledBorderColor = Gray300,
                errorBorderColor = Rose700
            ),
            modifier = modifier
                .fillMaxWidth()
                .defaultMinSize(
                    minHeight = 40.dp
                ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.W400
                )
            },
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = if (error) R.drawable.ic_info else if (visible) R.drawable.eye_open else R.drawable.eye_close),
                    contentDescription = "",
                    tint = if (error) Error500 else if (visible) MaterialTheme.colors.primary
                    else MaterialTheme.colors.onSurface,
                    modifier = Modifier.clickable {
                        visible = !visible
                    }
                )
            },
            textStyle = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.W400
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = modifier.height(6.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.subtitle2,
            fontWeight = FontWeight.W400,
            modifier = modifier,
            color = Error500
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputPasswordPrimary() {
    UwangTheme {
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
        ) {
            var visible by remember {
                mutableStateOf("")
            }
            InputPasswordPrimary(
                label = "Password",
                value = visible,
                onChange = { visible = it },
            )
            InputPasswordPrimary(
                label = "Password",
                value = visible,
                onChange = { visible = it },
            )
        }
    }
}