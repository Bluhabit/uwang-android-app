/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.Indicators
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.Neutral100
import com.bluhabit.core.ui.theme.Primary600
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun OnboardScreen(
    navHostController: NavHostController,
    stateFlow: Flow<OnboardState>,
    effectFlow: Flow<OnboardEffect>,
    onAction: (OnboardAction) -> Unit,
    modifier: Modifier = Modifier
) {

    val state by stateFlow.collectAsStateWithLifecycle(initialValue = OnboardState())
    val effect by effectFlow.collectAsState(initial = OnboardEffect.None)
    val scope = rememberCoroutineScope()

    val onboard = listOf(
        Triple(
            R.drawable.onboarding1, com.bluehabit.core.ui.R.string.text_title_1, com.bluehabit.core.ui.R.string.text_onboard_1
        ), Triple(
            R.drawable.onboarding2, com.bluehabit.core.ui.R.string.text_title_2, com.bluehabit.core.ui.R.string.text_onboard_2
        ), Triple(
            R.drawable.onboarding3, com.bluehabit.core.ui.R.string.text_title_3, com.bluehabit.core.ui.R.string.text_onboard_3
        ), Triple(
            R.drawable.onboarding3, com.bluehabit.core.ui.R.string.text_title_3, com.bluehabit.core.ui.R.string.text_onboard_3
        )
    )
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) { onboard.size }
    val googleAuthLauncher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
        when (it) {
            is Response.Error -> {
                Log.e("HEHE", it.message)
            }

            is Response.Result -> {
                onAction(OnboardAction.SignInGoogle(it.data))
            }
        }
    })
    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            OnboardEffect.None -> Unit
            is OnboardEffect.ShowDialog -> Unit
            OnboardEffect.NavigateAuth -> Unit
            OnboardEffect.NavigateHome -> navHostController.navigate("/home")
        }
    })
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HorizontalPager(
                state = pagerState, userScrollEnabled = true
            ) { page ->
                FrameScreen(
                    modifier = modifier,
                    top = {
                        Image(
                            painter = painterResource(onboard[page].first),
                            contentDescription = "",
                            modifier = modifier
                                .width(252.dp)
                                .height(252.dp),
                            contentScale = ContentScale.FillWidth
                        )
                        Text(
                            text = stringResource(onboard[page].second),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Center,
                            color = Primary600,
                            modifier = modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(onboard[page].third),
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                            color = Neutral100,
                            modifier = modifier.fillMaxWidth()
                        )

                    },
                    middle = { },
                    bottom = {
                        if (page == 3) {
                            StepFour(
                                onNavigateToSignUp = {
                                    navHostController.navigate("sign_up")
                                },
                                onNavigateToTermCondition = {
                                    navHostController.navigate("term_and_condition")
                                },
                                onNavigationToSignInEmail = {
                                    navHostController.navigate("sign_in")
                                },
                                onSignInGoogle = {
                                    googleAuthLauncher.launch(1)
                                }
                            )
                        } else {
                            ButtonPrimary(
                                modifier = modifier.fillMaxWidth(),
                                text = "Selanjutnya",
                                onClick = {
                                    scope.launch {
                                        pagerState.scrollToPage(page = page + 1)
                                    }
                                }
                            )
                        }
                    })
            }
        }
        FrameScreen(
            modifier = modifier,
            top = {},
            middle = { Indicators(size = 4, index = pagerState.currentPage) },
            bottom = {}
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                ),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Lewati", modifier = Modifier.clickable {
                scope.launch {
                    pagerState.scrollToPage(4)
                }
            })
        }
    }
}

@Composable
fun StepFour(
    onSignInGoogle: () -> Unit,
    onNavigationToSignInEmail: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToTermCondition: () -> Unit
) {
    ButtonGoogle(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.onboard_screen_sign_in_google_button_text),
        onClick = onSignInGoogle
    )
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(0.9f)
        )
        Text(
            text = stringResource(id = R.string.sign_in_screen_or),
            style = CustomTypography.Body.Small.W400,
            color = CustomColor.Neutral.Grey8,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.4f)
        )
        Divider(
            modifier = Modifier.weight(0.9f)
        )
    }
    ButtonOutlinedPrimary(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.onboard_screen_sign_in_email_button_text),
        onClick = onNavigationToSignInEmail
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp), horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_screen_not_have_an_account),
            style = CustomTypography.Body.Small.W400,
            color = CustomColor.Neutral.Grey9
        )
        Text(text = stringResource(id = R.string.sign_in_screen_register),
            style = CustomTypography.Body.Small.W400,
            color = CustomColor.Primary.Blue500,
            modifier = Modifier.clickable {
                onNavigateToSignUp()
            })
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_screen_term_and_condition_1),
            style = CustomTypography.Body.Small.W400,
            color = CustomColor.Neutral.Grey7
        )
        Text(text = stringResource(id = R.string.sign_in_screen_term_and_condition_2),
            style = CustomTypography.Body.Small.W400,
            color = CustomColor.Primary.Blue500,
            modifier = Modifier.clickable {
                onNavigateToTermCondition()
            })
    }
}

@Composable
fun FrameScreen(
    modifier: Modifier = Modifier,
    top: @Composable ColumnScope.() -> Unit = {},
    middle: @Composable () -> Unit = {},
    bottom: @Composable ColumnScope.() -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight(fraction = 0.6f)
                .padding(
                    start = 18.dp, end = 18.dp, bottom = 18.dp
                ), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom
        ) {
            top(this)
        }
        Column(
            modifier = modifier.fillMaxHeight(fraction = 0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
            ) {
                middle()
            }
        }
        Column(
            modifier = modifier
                .wrapContentHeight()
                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            bottom(this)
        }
    }
}