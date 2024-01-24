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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.ScreenFrameOnboarding
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.pager.Indicators
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.Neutral100
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

val onboard = listOf(
    Triple(
        R.drawable.onboarding1,
        R.string.text_title_1,
        R.string.text_onboard_1
    ), Triple(
        R.drawable.onboarding2,
        R.string.text_title_2,
        R.string.text_onboard_2
    ), Triple(
        R.drawable.onboarding3,
        R.string.text_title_3,
        R.string.text_onboard_3
    ), Triple(
        R.drawable.onboard4,
        R.string.onboard_screen_login,
        R.string.onboard_screen_login_title
    )
)

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
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)

    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) { onboard.size }
    val googleAuthLauncher = rememberLauncherForActivityResult(contract = GoogleAuthContract(), onResult = {
        when (it) {
            is Response.Error -> {
                Log.e("Sign In Google", it.message)
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
            OnboardEffect.NavigateHome -> navHostController.navigate("home")
            OnboardEffect.NavigateCompleteProfile -> navHostController.navigate("complete_profile")
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
                state = pagerState,
                userScrollEnabled = true
            ) { page ->
                ScreenFrameOnboarding(
                    modifier = modifier,
                    top = {
                        Column(
                            modifier = modifier
                                .padding(top = dimens.dp_30),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(onboard[page].second),
                                style = MaterialTheme.typography.h6.copy(
                                    fontSize = dimens.sp_20
                                ),
                                fontWeight = FontWeight.W600,
                                textAlign = TextAlign.Center,
                                color = UwangColors.Primary.Blue500,
                                modifier = modifier.fillMaxWidth()
                            )
                            Text(
                                text = stringResource(onboard[page].third),
                                style = MaterialTheme.typography.body2.copy(
                                    fontSize = dimens.sp_14
                                ),
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center,
                                color = Neutral100,
                                modifier = modifier.fillMaxWidth()
                            )
                        }
                        Image(
                            painter = painterResource(onboard[page].first),
                            contentDescription = "",
                            modifier = modifier
                                .fillMaxHeight(0.6f)
                                .fillMaxWidth()
                                .padding(horizontal = dimens.dp_20),
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = modifier.height(6.dp))
                    },
                    middle = { },
                    bottom = {
                        if (page == 3) {
                            StepFour(
                                onNavigateToSignUp = {
                                    navHostController.navigate("sign_up") {
                                        popUpTo("onboard") {
                                            inclusive = true
                                        }
                                    }
                                },
                                onNavigateToTermCondition = {
                                    navHostController.navigate("term_and_condition")
                                },
                                onNavigationToSignInEmail = {
                                    navHostController.navigate("sign_in") {
                                        popUpTo("onboard") {
                                            inclusive = true
                                        }
                                    }
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
                                        pagerState.animateScrollToPage(page = page + 1)
                                    }
                                }
                            )
                        }
                    })
            }
        }
        ScreenFrameOnboarding(
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
            color = UwangColors.Neutral.Grey8,
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_screen_not_have_an_account),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Neutral.Grey9
        )
        Text(text = stringResource(id = R.string.sign_in_screen_register),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Primary.Blue500,
            modifier = Modifier.clickable {
                onNavigateToSignUp()
            })
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
    Column{
        Text(
            text = stringResource(id = R.string.sign_in_screen_term_and_condition_1),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Neutral.Grey7
        )
        Text(text = stringResource(id = R.string.sign_in_screen_term_and_condition_2),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Primary.Blue500,
            modifier = Modifier.clickable {
                onNavigateToTermCondition()
            })
    }
}

@Preview
@Composable
fun PreviewOnboarding() {
    UwangTheme {
        OnboardScreen(
            navHostController = rememberNavController(),
            stateFlow = flowOf(),
            effectFlow = flowOf(),
            onAction = {}
        )
    }
}