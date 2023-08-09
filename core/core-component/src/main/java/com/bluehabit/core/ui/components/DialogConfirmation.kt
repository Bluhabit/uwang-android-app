/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweTheme
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun DialogConfirmation(
    show: Boolean = false,
    icon: (@Composable () -> Unit)? = null,
    action: @Composable () -> Unit = {},
    title: String = String.Empty,
    message: String = String.Empty,
    onDismiss: () -> Unit = {}
) {
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .width(328.dp)
                    .height(351.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                    .padding(start = 26.dp, top = 28.dp, end = 26.dp, bottom = 28.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(35.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(103.dp)
                    ) {
                        icon?.invoke()
                    }
                    Column(

                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight(600),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = message,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight(400),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .width(276.dp)
                                .height(40.dp)
                        )
                    }
                    action.invoke()
                }
            }
        }
    }
}

@Composable
@Preview
fun DefaultPreview() {
    GaweTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            DialogConfirmation(
                show = true,
                title = "Berhasil Daftar",
                message = "Tinggal 1 langkah lagi untuk melengkapi profile Anda.",
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.success_icon_purple),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(1.dp)
                            .width(103.dp)
                            .height(103.dp)
                    )
                },
                action = {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(276.dp)
                            .height(44.dp)
                    ) {
                        Text(text = "Lanjut")
                    }
                }
            )
        }
    }
}