/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.BaseInputTextPrimary
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray400
import com.bluehabit.core.ui.theme.Gray500


@Composable
fun InputDropDown(
    items: List<String>,
    placeholder: String = String.Empty,
    onSelected: (String) -> Unit = {},
    selectedPriority: String = String.Empty,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        BaseInputTextPrimary(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.inactiveflag),
                    contentDescription = "",
                    tint = Gray400
                )
            },
            trailingIcon = {
                if (expanded) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = "",
                        tint = Gray500
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = "",
                        tint = Gray500
                    )
                }
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.W400,
                    color = Gray500
                )
            },
            readOnly = true,
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = !expanded
                }
        )
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            properties = PopupProperties(usePlatformDefaultWidth = false),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach {
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.blueflag),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = it)
                        }

                    },
                    onClick = { onSelected(it) }
                )
            }

        }
    }
}

@Composable
@Preview
fun MenuSamplePreview() {
    GaweanTheme() {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 20.dp
            )
        ) {
            InputDropDown(
                items = listOf("Low", "Normal", "High", "Urgent"),
                placeholder = "Pilih prioritas tugas"
            )
        }
    }
}