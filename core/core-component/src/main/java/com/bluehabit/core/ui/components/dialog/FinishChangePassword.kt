/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import app.trian.mvi.ui.extensions.Empty
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray900

@Composable
fun DialogChangePassword(
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
fun FinishChangePasswordPreview() {
    GaweanTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            DialogChangePassword(
                show = true,
                title = stringResource(id = R.string.text_label_confirm_dialog_password),
                message = stringResource(id = R.string.text_subtitle_confirm_dialog_password),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.alert),
                        contentDescription = "",
                        modifier = Modifier
                    )
                },
                action = {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(276.dp)
                            .height(44.dp)
                    ) {
                        Text(text = stringResource(id = R.string.text_button_confirm_dialog_password))
                    }
                }
            )
        }
    }
}