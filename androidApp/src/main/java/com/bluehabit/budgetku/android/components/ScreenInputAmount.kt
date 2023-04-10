/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Yellow800

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
            "0"
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
            "06"
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
fun ScreenInputAmount(
    value: String = "",
    onSubmit: () -> Unit = {},
    onClear: () -> Unit = {},
    onRemove: () -> Unit = {},
    onDismiss: () -> Unit = {}
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

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onDismiss
                )
            )
            Text(
                text = "Input Nominal",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                textAlign = TextAlign.Right
            )
            Divider()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        currentHeight / 2
                    )
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
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
                                            .clickable { }
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
                ButtonPrimary(text = "Simpan", onClick = onSubmit)
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenInputAmount() {
    BaseMainApp {
        ScreenInputAmount(
            value = "1.000.000"
        )
    }
}