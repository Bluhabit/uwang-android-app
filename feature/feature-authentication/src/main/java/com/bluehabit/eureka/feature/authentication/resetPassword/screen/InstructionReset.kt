/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.theme.GaweTheme
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.authentication.R

@Composable
fun InstructionReset(
    openEmail: () -> Unit = {},
    tryAgain: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = 28.dp,
                horizontal = 26.dp
            ),
        verticalArrangement = Arrangement.spacedBy(95.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo),
                contentDescription ="logo"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Instruksi Terkirim!",
                    style = MaterialTheme.typography.h6,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    text = "Cek email anda dan klik link pada instruksi tersebut.",
                    style = MaterialTheme.typography.body2,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
            }
            Column(
                Modifier.padding(30.dp)
            ) {
                Image(
                    painter = painterResource(id = com.bluehabit.core.ui.R.drawable.hero_intruction_reset_password),
                    contentDescription ="logo"
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(95.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = openEmail) {
                Text(
                    text = "Buka Email",
                    color = Color(0xFFFFFFFF)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tidak menerima email? Coba cek spam",
                    style = MaterialTheme.typography.caption,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    text = "atau",
                    style = MaterialTheme.typography.caption,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            tryAgain()
                        },
                    text = "coba lagi dengan alamat email yang lain.",
                    style = MaterialTheme.typography.body2,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = Primary600
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun DefaultPreview() {
    GaweTheme(darkTheme = false) {
        Column(modifier = Modifier.padding(20.dp)) {
            InstructionReset()
        }
    }
}