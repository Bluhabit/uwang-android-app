/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.FirstOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.FourthOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.GetStartedScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.ScreenFrameOnBoard
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.SecondOnboardScreen
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.ThirdOnboardScreen
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.theme.CustomTypography
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
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) { 4 }
    var isOnBoaardFinished by remember {
        mutableStateOf(false)
    }
    val progressAnimation = remember {
        Animatable(0f)
    }

    fun finishedOnBoard() {
        isOnBoaardFinished = true
    }

    fun nextScreen() {
        if (pagerState.currentPage > 2) {
            finishedOnBoard()
        } else {
            scope.launch {
                pagerState.scrollToPage(
                    pagerState.currentPage + 1
                )
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
            OnboardEffect.NavigateCompleteProfile -> navHostController.navigate("complete_profile")
        }

    })
    LaunchedEffect(key1 = pagerState.currentPage , block = {
        progressAnimation.snapTo(0f)
        progressAnimation.animateTo(
            1f,
            animationSpec = tween(
                10000,
                easing = LinearEasing
            )
        )
        nextScreen()
    })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        if (isOnBoaardFinished) {
            GetStartedScreen(
                onBackToOnboard = {
                    scope.launch {
                        pagerState.scrollToPage(
                            0
                        )
                        progressAnimation.snapTo(0f)
                    }
                    isOnBoaardFinished = false
                }
            )
        } else {
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
                skipOnboard = { finishedOnBoard() },
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
    }
}


@Composable
fun StepFour(
    onSignInGoogle: () -> Unit,
    onNavigationToSignInEmail: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToTermCondition: () -> Unit,

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
            text = stringResource(id = R.string.label_divider),
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
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = stringResource(id = R.string.placeholder_teks_register),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Neutral.Grey9
        )
        Text(text = stringResource(id = R.string.label_teks_button_register),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Primary.Blue500,
            modifier = Modifier.clickable {
                onNavigateToSignUp()
            })
    }
    Spacer(modifier = Modifier.padding(bottom = 16.dp))
    Column {
        Text(
            text = stringResource(id = R.string.onboard_screen_term_and_condition_1),
            style = CustomTypography.Body.Small.W400,
            color = UwangColors.Neutral.Grey7
        )
        Text(text = stringResource(id = R.string.onboard_screen_term_and_condition_2),
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

