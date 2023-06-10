/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.emailVerification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerificationSuccess
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerifyingEmail
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import kotlinx.coroutines.delay


@Composable
fun ScreenEmailVerification(
    state: EmailVerificationState = EmailVerificationState(),
    data: EmailVerificationDataState = EmailVerificationDataState(),
    invoker: UIListenerData<EmailVerificationState, EmailVerificationDataState, EmailVerificationEvent>
) = UiWrapperData(invoker = invoker) {

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
                navigateAndReplaceAll(Routes.CompleteProfile.routeName)
            }
        )
    }

}

@Preview
@Composable
fun PreviewScreenEmailVerification() {
    BaseMainApp {
        ScreenEmailVerification(
            invoker = UIListenerData(
                controller = rememberUIController(),
                state = EmailVerificationState(),
                data = EmailVerificationDataState()
            )
        )
    }
}