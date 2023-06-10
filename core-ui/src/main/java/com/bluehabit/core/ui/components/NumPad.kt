/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Yellow800

data class ButtonInput(
    val label: String = "",
    val value: String = ""
)

val buttons = mapOf(
    "1" to listOf(
        ButtonInput(
            "7",
            "7"
        ),
        ButtonInput(
            "8",
            "8"
        ),
        ButtonInput(
            "9",
            "9"
        )
    ),
    "2" to listOf(
        ButtonInput(
            "4",
            "4"
        ),
        ButtonInput(
            "5",
            "5"
        ),
        ButtonInput(
            "6",
            "6"
        )
    ),
    "3" to listOf(
        ButtonInput(
            "1",
            "1"
        ),
        ButtonInput(
            "2",
            "2"
        ),
        ButtonInput(
            "3",
            "3"
        )
    ),
    "4" to listOf(
        ButtonInput(
            "0",
            "0"
        ),
        ButtonInput(
            "000",
            "000"
        )
    )
)

@Composable
fun NumPad(
    onSubmit: () -> Unit = {},
    onClear: () -> Unit = {},
    onRemove: () -> Unit = {},
    onChange: (String) -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val currentHeight = ctx
        .resources
        .displayMetrics.heightPixels.dp /
            LocalDensity.current.density
    val buttonHeight = (currentHeight / 5)
    val buttonSize = currentWidth / 5

    Divider()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(
                currentHeight / 2
            )
            .padding(
                horizontal = 20.dp,
                vertical = 6.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .width(
                        currentWidth - (buttonSize + 30.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                buttons.forEach {
                    Row(
                        modifier = Modifier.width(currentWidth - buttonSize),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        it.value.forEach { btn ->
                            Box(
                                modifier = Modifier
                                    .clickable(
                                        enabled = true,
                                        onClick = { onChange(btn.value) }
                                    )
                                    .height(buttonSize)
                                    .width(buttonSize + 10.dp)
                            ) {
                                Text(
                                    text = btn.label,
                                    style = MaterialTheme.typography.h4,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )
                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .width(buttonSize),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            buttonHeight - 20.dp
                        )
                        .clip(
                            MaterialTheme.shapes.small
                        )
                        .border(
                            width = 1.dp,
                            shape = MaterialTheme.shapes.medium,
                            color = Grey300
                        )
                        .clickable(
                            enabled = true,
                            onClick = onClear
                        )
                        .padding(
                            horizontal = 10.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "C",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = Yellow800
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            buttonHeight - 20.dp
                        )
                        .clip(
                            MaterialTheme.shapes.small
                        )
                        .border(
                            width = 1.dp,
                            shape = MaterialTheme.shapes.medium,
                            color = Grey300
                        )
                        .clickable(enabled = true, onClick = onRemove)
                        .padding(
                            horizontal = 10.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "",
                        tint = Yellow800
                    )
                }
            }

        }
        ButtonPrimary(text = stringResource(R.string.text_button_input_amount_num_pad), onClick = onSubmit)
    }
}

@Preview
@Composable
fun PreviewNumPad() {
    BaseMainApp {
        NumPad()
    }
}