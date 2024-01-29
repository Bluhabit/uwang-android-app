/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun SecondOnboardScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .shadow(0.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector_onboard_2),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        Column(

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                ),
            ) {
                Column(
                    modifier = modifier
                        .padding(vertical = 24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.title_onboarding_satu),
                        style = UwangTypography.DisplayXS.SemiBold,
                        color = Color.White,
                    )
                }
            }
            Image(
                painter = painterResource(R.drawable.onboarding_2),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

}

@Preview
@Composable
fun PreviewSecondScreen() {
    UwangTheme {
        SecondOnboardScreen()
    }
}