/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.feature.authentication.signIn.SignIn
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.UIWrapper
import com.bluehabit.core.ui.UIWrapperListener
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.rememberUIController

object Onboard {
    const val routeName = "Onboard"

    val images: List<Int> = listOf(
        R.drawable.onboard_1,
        R.drawable.onboard_2,
        R.drawable.onboard_3,
        R.drawable.onboard_4,
    )
    val title: List<Int> = listOf(
        R.string.onboard_title_1,
        R.string.onboard_title_2,
        R.string.onboard_title_3,
        R.string.onboard_title_4,
    )
    val subtitle: List<Int> = listOf(
        R.string.onboard_subtitle_1,
        R.string.onboard_subtitle_2,
        R.string.onboard_subtitle_3,
        R.string.onboard_subtitle_4,
    )
}

@Composable
fun ScreenOnboard(
    state: OnboardState = OnboardState(),
    invoker: UIWrapperListener<OnboardState, OnboardEvent>
) = UIWrapper(invoker = invoker) {

    val pagerState = rememberPagerState(
        initialPage = 0
    )

    val percentage by remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> 0.25f
                1 -> 0.50f
                2 -> 0.75f
                3 -> 1f
                else -> 0.1f
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            pageCount = 4
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 100.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = Onboard.images[it]),
                    contentDescription = stringResource(id = Onboard.title[it]),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            264.dp
                        ),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = Onboard.title[it]),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = stringResource(id = Onboard.subtitle[it]),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        LinearProgressIndicator(
            progress = percentage,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(
                    top = 40.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .height(8.dp),
            strokeCap = StrokeCap.Round
        )
        ButtonPrimary(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 40.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            text = "Get Started",
        ) {
            navigateAndReplaceAll(
                SignIn.routeName
            )
        }
    }
}

@Preview
@Composable
fun PreviewScreenOnboard() {
    BaseMainApp {
        ScreenOnboard(
            invoker = UIWrapperListener(
                controller = rememberUIController(),
                state = OnboardState()
            )
        )
    }
}