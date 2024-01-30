/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.Gray900
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun FirstOnboardScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val ctx = LocalContext.current
        val dimens = UwangDimens.from(ctx)
        Column(
            modifier = modifier
                .padding(vertical = dimens.dp_24, horizontal = dimens.dp_16)
        ) {
            Text(
                text = stringResource(R.string.title_onboarding_satu),
                style = UwangTypography.DisplayXS.SemiBold,
                color = Gray900,
            )
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