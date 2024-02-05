/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.common.loadHtmlFromAssets
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.FirstOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.FourthOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.GetStartedScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.ScreenFrameOnBoard
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.SecondOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.ThirdOnboardScreen
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.components.dialog.DialogLoading
import com.bluhabit.core.ui.components.screen.TermAndConditionScreen
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

val onboard = listOf(
    Triple(
        R.drawable.onboarding_1,
        R.string.title_onboarding_satu,
        R.string.text_onboard_1
    ),
    Triple(
        R.drawable.onboarding_2,
        R.string.title_onboarding_dua,
        R.string.text_onboard_2
    ),
    Triple(
        R.drawable.onboarding_3,
        R.string.title_onboarding_tiga,
        R.string.text_onboard_3
    ),
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
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) { 4 }

    val progressAnimation = remember {
        Animatable(0f)
    }

    fun nextScreen() {
        if (pagerState.currentPage > 2) {
            onAction(OnboardAction.OnChangeCurrentScreen(1))
        } else {
            scope.launch {
                pagerState.scrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    fun prevScreen() {
        scope.launch {
            pagerState.scrollToPage(
                pagerState.currentPage - 1
            )
        }
    }

    val backgroundModifier = when (pagerState.currentPage) {
        0, 2 -> Modifier.background(MaterialTheme.colors.surface)
        1, 3 -> Modifier.background(
            Brush.verticalGradient(
                colors = listOf(
                    UwangColors.Primary.Blue500,
                    UwangColors.Primary.Blue1000
                )
            )
        )

        else -> Modifier.background(MaterialTheme.colors.surface)
    }

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
            OnboardEffect.NavigateToPersonalize -> navHostController.navigate("complete_profile")
        }
    })
    LaunchedEffect(key1 = Unit, block = {
        onAction(OnboardAction.CheckSession)
    })
    LaunchedEffect(key1 = pagerState.currentPage, block = {
        progressAnimation.snapTo(0f)
        progressAnimation.animateTo(
            1f,
            animationSpec = tween(
                10_000,
                easing = LinearEasing
            )
        )
        nextScreen()
    })
    BackHandler {
        if (state.currentScreen <= 1) {
            navHostController.navigateUp()
        } else {
            onAction(OnboardAction.OnChangeCurrentScreen(screen = state.currentScreen - 1))
        }
    }

    DialogLoading(show = state.showLoading)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        when (state.currentScreen) {
            0 -> {
                ScreenFrameOnBoard(
                    modifier = backgroundModifier,
                    headerTextColor = when (pagerState.currentPage) {
                        0, 2 -> UwangColors.Text.Secondary
                        1, 3 -> Color.White
                        else -> Color.White
                    },
                    indicatorColor = when (pagerState.currentPage) {
                        0, 2 -> UwangColors.State.Primary.Main
                        1, 3 -> Color.White
                        else -> Color.White
                    },
                    currentPage = pagerState.currentPage,
                    skipOnboard = {
                        onAction(OnboardAction.OnChangeCurrentScreen(1))
                    },
                    nextScreen = {
                        nextScreen()
                    },
                    progressState = progressAnimation.value,
                    prevScreen = {
                        prevScreen()
                    },
                    content = {
                        HorizontalPager(
                            state = pagerState,
                        ) { page ->

                            when (page) {
                                0 -> FirstOnboardScreen()
                                1 -> SecondOnboardScreen()
                                2 -> ThirdOnboardScreen()
                                3 -> FourthOnboardScreen()
                            }
                        }
                    }
                )
            }

            1 -> {
                GetStartedScreen(
                    onBackToOnboard = {
                        scope.launch {
                            pagerState.scrollToPage(
                                0
                            )
                            progressAnimation.snapTo(0f)
                        }
                        onAction(OnboardAction.OnChangeCurrentScreen(0))
                    },
                    onNavigateToTermCondition = {
                        onAction(OnboardAction.OnChangeCurrentScreen(2))
                    },
                    onNavigationToSignIn = {
                        navHostController.navigate("sign_in") {
                            launchSingleTop = true
                        }
                    },
                    onSignInGoogle = {
                        googleAuthLauncher.launch(1)
                    },
                    onSignUp = {
                        navHostController.navigate("sign_up") {
                            launchSingleTop = true
                        }
                    }
                )
            }

            2 -> {
                TermAndConditionScreen(
                    htmlString = loadHtmlFromAssets(
                        context = ctx,
                        fileName = "termAndCondition.html"
                    ),
                    onBackPressed = {
                        onAction(OnboardAction.OnChangeCurrentScreen(1))
                    }
                )
            }
        }
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

