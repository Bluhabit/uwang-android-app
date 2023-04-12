/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.checkEmailResetPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.rememberApplicationState

object CheckEmailResetPassword {
    const val routeName = "CheckEmailResetPassword"
}

fun NavGraphBuilder.routeCheckEmailResetPassword(
    state: ApplicationState,
) {
    composable(CheckEmailResetPassword.routeName) {
        CheckEmailResetPassword(
            state
        )
    }
}

@Composable
internal fun CheckEmailResetPassword(appState: ApplicationState) = UIWrapper<ResetPasswordViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.logo_email_notify), contentDescription = "")
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.title_check_email_reset_password),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = stringResource(id = R.string.detail_check_email_reset_password) + " @${state.email}",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF616161),
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 40.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ButtonPrimary(
                text = stringResource(id = R.string.open_email_check_email_reset_password),
                onClick = { dispatch(CheckEmailResetPasswordEvent.OpenEmailApplication) }
            )
            ButtonOutlinedPrimary(
                text = stringResource(id = R.string.try_another_email_check_email_reset_password),
                onClick = { dispatch(CheckEmailResetPasswordEvent.ChangeEmailForResetPassword) }
            )
        }

    }

}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        CheckEmailResetPassword(
            appState = rememberApplicationState()
        )
    }
}

