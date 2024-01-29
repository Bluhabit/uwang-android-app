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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.onboard.screen.FirstOnboardScreen
import com.bluhabit.blu.data.common.Response
import com.bluhabit.blu.data.contract.GoogleAuthContract
import com.bluhabit.core.ui.components.button.ButtonGoogle
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

val onboard = listOf(
    Triple(
        R.drawable.onboarding_1,
        R.string.title_onboarding_satu,
        R.string.text_onboard_1
    ), Triple(
        R.drawable.onboarding_2,
        R.string.title_onboarding_dua,
        R.string.text_onboard_2
    ), Triple(
        R.drawable.onboarding_3,
        R.string.title_onboarding_tiga,
        R.string.text_onboard_3
    ), Triple(
        R.drawable.onboarding_4,
        R.string.title_onboarding_empat,
        R.string.text_onboard_4
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
//        Indicators(size = 4, index = pagerState.currentPage)

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = true
            ) { page ->
                when (page) {
                    0 -> FirstOnboardScreen(
                        indicator = {
                            LinearProgressIndicator(
                                progress = 100f,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(4.dp),
                                color = UwangColors.State.Primary.Main
                            )
                            LinearProgressIndicator(
                                progress = 100f,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(4.dp),
                                color = UwangColors.Neutral.Grey3
                            )
                            LinearProgressIndicator(
                                progress = 100f,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(4.dp),
                                color = UwangColors.Neutral.Grey3
                            )
                            LinearProgressIndicator(
                                progress = 100f,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(4.dp),
                                color = UwangColors.Neutral.Grey3
                            )
                        },
                        header = {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(

                                    ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.app_logo),
                                    contentDescription = "blu_logo",
                                    modifier = modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.label_header_logo),
                                    style = UwangTypography.BodyMedium.Regular,
                                    color = UwangColors.Text.Secondary,
                                )
                                Spacer(modifier = modifier.weight(1f))
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = "ic_close"
                                )
                            }
                        }
                    )
                    1 -> FirstOnboardScreen()
                    2 -> FirstOnboardScreen()
                    3 -> FirstOnboardScreen()
                    else -> FirstOnboardScreen()
                }
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
        horizontalArrangement = Arrangement.Start
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