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
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@Navigation(
    route = Routes.Onboard.routeName,
    group = AuthenticationConstants.parentRoute,
    viewModel = OnboardViewModel::class
)
@Composable
fun OnboardScreen(
    uiContract: UIContract<OnboardState,OnboardIntent,OnboardAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(uiContract = uiContract) {

    val pagerState = rememberPagerState(
        initialPage = 0
    )
    val totalPage= 4

    val percentage by remember {
        derivedStateOf { (((pagerState.currentPage+1).toFloat() / totalPage) * 100) / 100 }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            count = totalPage,
            userScrollEnabled=true
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
                    painter = painterResource(id = Routes.Onboard.images[0]),
                    contentDescription = stringResource(id = Routes.Onboard.title[0]),
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
                        text = stringResource(id = Routes.Onboard.title[0]),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = stringResource(id = Routes.Onboard.subtitle[0]),
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
            navigator.navigateAndReplace(Routes.SignIn.routeName)
        }
    }
}

@Preview
@Composable
fun PreviewOnboardScreen() {
    MaterialTheme {
        OnboardScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = OnboardState()
            )
        )
    }
}