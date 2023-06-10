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
import com.bluehabit.budgetku.feature.authentication.completeProfile.CompleteProfile
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerificationSuccess
import com.bluehabit.budgetku.feature.authentication.emailVerification.components.ScreenVerifyingEmail
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.UIWrapper
import com.bluehabit.core.ui.UIWrapperListenerData
import com.bluehabit.core.ui.rememberUIController
import kotlinx.coroutines.delay

object EmailVerification {
    const val routeName = "EmailVerification"
}


@Composable
fun ScreenEmailVerification(
    state: EmailVerificationState = EmailVerificationState(),
    data: EmailVerificationDataState = EmailVerificationDataState(),
    invoker: UIWrapperListenerData<EmailVerificationState, EmailVerificationDataState, EmailVerificationEvent>
) = UIWrapper(invoker = invoker) {

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
                navigateAndReplaceAll(CompleteProfile.routeName)
            }
        )
    }

}

@Preview
@Composable
fun PreviewScreenEmailVerification() {
    BaseMainApp {
        ScreenEmailVerification(
            invoker = UIWrapperListenerData(
                controller = rememberUIController(),
                state = EmailVerificationState(),
                data = EmailVerificationDataState()
            )
        )
    }
}