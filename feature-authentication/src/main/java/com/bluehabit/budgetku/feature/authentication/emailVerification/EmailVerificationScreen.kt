/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.emailVerification

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerificationSuccess
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerifyingEmail
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes
import kotlinx.coroutines.delay

@Navigation(
    route = Routes.EmailVerification.routeName,
    group = AuthenticationConstants.parentRoute,
    viewModel = EmailVerificationViewModel::class
)
@Composable
fun EmailVerificationScreen(
    uiContract: UIContract<EmailVerificationState, EmailVerificationIntent, EmailVerificationAction>
) = UIWrapper(uiContract) {

    LaunchedEffect(key1 = this, block = {
        delay(3000)
        commit {
            copy(
                isLoading = false
            )
        }
    })

    if (state.isLoading) {
        ScreenVerifyingEmail()
    } else {
        ScreenVerificationSuccess(
            onConfirm = {
                navigator.navigateAndReplace(Routes.CompleteProfile.routeName)
            }
        )
    }

}

@Preview
@Composable
fun PreviewScreenEmailVerification() {
    MaterialTheme {
        EmailVerificationScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = EmailVerificationState()
            )
        )
    }
}