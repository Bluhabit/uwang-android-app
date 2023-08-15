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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
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
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900
/**
 * DialogConfirmation
 * @param show
 * for show overlay dialog
 * @param icon
 * for icon
 * @param action
 * button click
 * @param title
 * title dialog
 * @param message
 * message dialog
 * @param onDismiss
 *
 * @sample DialogConfirmationPreview
 * */

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
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        vertical = 28.dp,
                        horizontal = 26.dp,
                    ),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    icon?.invoke()
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.Top
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()

                        ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h6,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center,
                            color = Gray900
                        )
                        Text(
                            text = message,
                            style = MaterialTheme.typography.body2,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                            color = Gray900,
                        )
                    }
                    action.invoke()
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun DialogConfirmationPreview() {
    GaweanTheme {
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