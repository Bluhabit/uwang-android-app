/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.emailVerification.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.ui.Grey900

@Composable
fun ScreenVerificationSuccess(
    onConfirm: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.email_check),
                contentDescription = stringResource(R.string.text_title_email_check),
                modifier = Modifier.size(130.dp),

                )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.text_complete_verify_email_check),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Grey900
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.text_message_email_check),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Normal
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ButtonPrimary(
                text = stringResource(R.string.text_button_complete_profile_email_check),
                onClick = onConfirm
            )
        }
    }
}

@Preview
@Composable
fun PreviewScreenVerificationSuccess() {
    BaseMainApp {
        ScreenVerificationSuccess()
    }
}