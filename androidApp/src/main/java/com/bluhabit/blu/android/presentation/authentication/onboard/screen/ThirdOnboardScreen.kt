/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ThirdOnboardScreen(
    modifier: Modifier = Modifier,
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector_onboard_3),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimens.dp_16,
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = dimens.dp_24)
            ) {
                Text(
                    text = stringResource(R.string.title_onboarding_tiga),
                    style = UwangTypography.DisplayXS.SemiBold,
                    color = Gray900,
                )
            }
            Box (
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(R.drawable.onboarding_3),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                )
            }

        }
    }

}

@Preview
@Composable
fun PreviewThirdScreen() {
    UwangTheme {
        ThirdOnboardScreen()
    }
}