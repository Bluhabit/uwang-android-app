/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.checkbox.CheckedCircleCheckBox
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray400
import com.bluehabit.core.ui.theme.Gray50
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun InputSubTask(
    modifier: Modifier = Modifier,
    value: String = String.Empty,
    maxLine: Int = Int.MAX_VALUE,
    maxLength: Int = 100,
    enabled: Boolean = true,
    checked: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onCheckedChange: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray50)
            .padding(start = 10.dp)
    ) {
        CheckedCircleCheckBox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = value.isNotEmpty(),
            modifier = Modifier
                .padding(top = 14.dp)
        )
        BasicTextField(
            value = value,
            textStyle = TextStyle(
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
            ),
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = value,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_placeholder_input_subtask),
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                        )
                    },
                    label = null,
                    leadingIcon = null,
                    trailingIcon = null,
                    singleLine = false,
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.textFieldColors(
                        placeholderColor = Gray400,
                        textColor = Gray900,
                    )
                )
            },
            maxLines = maxLine,
            modifier = Modifier
                .weight(1f)
        )
        IconButton(onClick = onDeleteClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                tint = Gray900,
                contentDescription = stringResource(id = R.string.text_description_ic_delete_input_subtask),
                modifier = Modifier
                    .padding(top = 6.dp),
            )
        }
    }
}

@Preview(device = Devices.DEFAULT)
@Composable
fun InputSubTaskPreview() {
    val inputState = remember {
        mutableStateOf("")
    }
    val checkedState = remember {
        mutableStateOf(false)
    }
    GaweanTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(12.dp)
        ) {
            InputSubTask(
                value = inputState.value,
                onValueChange = {
                    // Please make sure swipe checkedState to false if TextField is empty
                    if (it.isEmpty()) {
                        checkedState.value = false
                    }
                    inputState.value = it
                },
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = !checkedState.value
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}