/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun DialogPrimary(
    show: Boolean = false,
    dismissIcon: (@Composable RowScope.() -> Unit)? = null,
    image: (@Composable RowScope.() -> Unit)? = null,
    action: (@Composable RowScope.() -> Unit)? = null,
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
                        shape = RoundedCornerShape(24.dp)
                    )
            ) {
                if (dismissIcon != null) {
                    Row(
                        modifier = Modifier
                            .padding(top = 27.dp, end = 29.dp, bottom = 14.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        content = dismissIcon
                    )
                }
                if (image != null) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(bottom = 43.dp)
                            .fillMaxWidth(),
                        content = image
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.Top
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 17.dp, end = 17.dp, bottom = 20.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = title,
                        style = CustomTypography.Body.XL.W600,
                        color = UwangColors.Neutral.Grey13,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = message,
                        style = CustomTypography.Body.Small.W400,
                        color = UwangColors.Neutral.Grey9,
                        textAlign = TextAlign.Center
                    )
                }
                if (action != null) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 17.dp, end = 17.dp, bottom = 50.dp)
                            .fillMaxWidth(),
                        content = action
                    )
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun DialogPrimaryPreview() {
    UwangTheme {
        DialogPrimary(
            show = true,
            title = stringResource(id = R.string.text_title_success_profile),
            message = stringResource(id = R.string.text_subtitle_success_profile),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.success),
                    contentDescription = "",
                    modifier = Modifier
                )
            },
            dismissIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    tint = UwangColors.Neutral.Grey12,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            // On Dismiss
                        }
                )
            },
            action = {
                ButtonPrimary(
                    text = stringResource(id = R.string.text_btn_success_profile),
                    onClick = {
                        // On Action
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        )
    }
}