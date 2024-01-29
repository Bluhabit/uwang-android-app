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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.Gray900
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun FirstOnboardScreen(
    modifier: Modifier = Modifier,
    indicator: @Composable RowScope.() -> Unit = {},
    header: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 26.dp
            ),
        ) {
            Row(
                modifier = modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                indicator()
            }
            header()
            Column(
                modifier = modifier
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_onboarding_satu),
                    style = UwangTypography.DisplayXS.SemiBold,
                    color = Gray900,
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.onboarding_1),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun PreviewFirstScreen() {
    UwangTheme {
        FirstOnboardScreen()
    }
}