/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey900

@Composable
fun BottomSheetConfirmationEmail(
    email: String = "",
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {

    BaseBottomSheet(
        onDismiss = onDismiss,
        onConfirm = onConfirm,
        textConfirmation = "Verifikasi Sekarang",
        enableConfirmation = true
    ) {
        Column( modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(R.drawable.email_send),
                contentDescription = "Logo_email",
                modifier = Modifier.size(115.dp),

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Verifikasi Email Kamu",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = Grey900
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                textAlign= TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                            fontWeight = FontWeight.Normal
                        ),
                    ) {
                        append("Kami baru saja mengirim link verifikasi ke email ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.primary
                        ),
                    ) {
                        append(email)
                    }
                    withStyle(
                        style = SpanStyle(
                            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                            fontWeight = FontWeight.Normal,
                        ),
                    ) {
                        append("Cek email kamu dan klik button verifikasi yang diberikan.")
                    }

                },

            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun PreviewContentBottomSheetSignUp() {
    BaseMainApp {
        BottomSheetConfirmationEmail()
    }
}


