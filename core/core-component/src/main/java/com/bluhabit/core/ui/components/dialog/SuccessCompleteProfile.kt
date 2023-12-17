/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun DialogSuccessProfile(
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
                            color = Color(0xFF0E0F0C)
                        )
                        Text(
                            text = message,
                            style = MaterialTheme.typography.body2,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF4F504E),
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
fun FinishDialogSuccessProfile() {
    UwangTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            DialogChangePassword(
                show = true,
                title = stringResource(id = R.string.text_title_success_profile),
                message = stringResource(id = R.string.text_subtitle_success_profile),
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.success),
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
                        Text(text = stringResource(id = R.string.text_btn_success_profile))
                    }
                }
            )
        }
    }
}