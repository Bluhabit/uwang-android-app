/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.resetPassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.rememberApplicationState

object ResetPassword {
    const val routeName = "ResetPassword"
}

fun NavGraphBuilder.routeResetPassword(
    state: ApplicationState,
) {
    composable(ResetPassword.routeName) {
        ScreenResetPassword(
            state
        )
    }
}

@Composable
internal fun ScreenResetPassword(appState: ApplicationState) = UIWrapper<ResetPasswordViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState()
    with(appState) {
        setupTopAppBar {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                navigateUp()
                            }
                    )
                },
                backgroundColor = Color.White,
                title = {
                    Text(text = "")
                },
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        with(appState) {
            setupTopAppBar {
                TopAppBar(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    elevation = 0.dp,
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    navigateUp()
                                }
                        )
                    },
                    backgroundColor = Color.White,
                    title = {
                        Text(text = "")
                    },
                )
            }
        }
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.title_send_link_to_email_for_reset_password),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
            )
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = stringResource(id = R.string.detail_send_link_to_email_for_reset_password),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF616161),
            )
            FormInput(
                value = state.email,
                label = stringResource(id = R.string.label_email_send_link_to_email_for_reset_password),
                placeholder = stringResource(id = R.string.placeholder_send_link_to_email_for_reset_password),
                onChange = {
                    commit { copy(email = it) }
                    dispatch(ResetPasswordEvent.ValidateEmailAddress)
                }
            )
        }

        ButtonPrimary(
            text = stringResource(id = R.string.label_button_send_link_to_email_for_reset_password),
            enabled = state.isButtonEnabled,
            onClick = { dispatch(ResetPasswordEvent.SendLinkResetPasswordToEmail(state.email)) }
        )

    }

}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenResetPassword(
            appState = rememberApplicationState()
        )
    }
}

