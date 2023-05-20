/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.emailVerification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.auth.completeProfile.CompleteProfile
import com.bluehabit.budgetku.android.feature.auth.emailVerification.components.ScreenVerificationSuccess
import com.bluehabit.budgetku.android.feature.auth.emailVerification.components.ScreenVerifyingEmail
import kotlinx.coroutines.delay

object EmailVerification {
    const val routeName = "EmailVerification"
}

fun NavGraphBuilder.routeEmailVerification(
    state: ApplicationState,
) {
    composable(EmailVerification.routeName) {
        ScreenEmailVerification(appState = state)
    }
}

@Composable
internal fun ScreenEmailVerification(
    appState: ApplicationState,
) = UIWrapper<EmailVerificationViewModel>(appState = appState) {
    val state by uiState.collectAsState()
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
        ScreenEmailVerification(it)
    }
}