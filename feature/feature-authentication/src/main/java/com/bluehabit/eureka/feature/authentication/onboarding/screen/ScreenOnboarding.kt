/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.onboarding.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.button.ButtonPrimaryVariant
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Primary50
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun ScreenOnboarding(
    modifier: Modifier = Modifier,
    navigateToAuth: () -> Unit = {}
) {
    val pagerState = rememberPagerState(0)
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = 18.dp,
                vertical = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Image(
                painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo),
                contentDescription = stringResource(id = com.bluehabit.core.ui.R.string.content_description_logo)
            )
        }
        Column {
            HorizontalPager(
                count = Routes.Onboard.itemsOnboarding.size,
                state = pagerState
            ) {
                Column(
                    modifier = modifier
                        .width(320.dp.from(ctx))
                        .height(340.dp.from(ctx))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Primary50)
                        .padding(
                            vertical = 10.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween

                ) {
                    Image(
                        painter = painterResource(id = Routes.Onboard.itemsOnboarding[pagerState.currentPage].first),
                        contentDescription = "",
                        modifier = modifier
                            .width(252.dp.from(ctx))
                            .height(252.dp.from(ctx))
                    )
                    Text(
                        text = stringResource(id = Routes.Onboard.itemsOnboarding[pagerState.currentPage].second),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(
                            horizontal = 16.dp
                        )
                    )

                }
            }
        }
        Column {
            PagerIndicator(
                size = 4,
                index = pagerState.currentPage
            )
        }
        Column {
            ButtonPrimary(
                modifier = modifier.fillMaxWidth(),
                text = "Lanjut",
                onClick = {
                    scope.launch {
                        if (pagerState.currentPage >= 3) {
                            navigateToAuth()
                        } else {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )
            Spacer(modifier = modifier.height(16.dp))
            ButtonPrimaryVariant(
                modifier = modifier.fillMaxWidth(),
                text = "Lewati",
                onClick = {
                    navigateToAuth()
                }
            )
        }
    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    index: Int,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Indicators(size = size, index = index)

    }
}

@Composable
fun BoxScope.Indicators(m: Modifier = Modifier, size: Int, index: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = m
            .align(Alignment.Center)
            .fillMaxWidth(),
    ) {
        repeat(size) {
            Spacer(modifier = Modifier.width(12.dp))
            Indicator(isSelected = it == index)
        }
    }

}

@Composable
fun Indicator(modifier: Modifier = Modifier, isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 24.dp else 12.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    Box(
        modifier = modifier
            .height(4.dp)
            .width(width.value)
            .clip(RoundedCornerShape(3.dp))
            .background(
                color =
                if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(
                    alpha = 0.5f
                )
            )
    ) {

    }
}

@Preview
@Composable
fun PreviewScreenOnboarding() {
    GaweanTheme {
        ScreenOnboarding()
    }
}