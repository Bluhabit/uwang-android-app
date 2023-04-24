/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.createNewPassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.components.input.FormInputPassword
import com.bluehabit.budgetku.android.rememberApplicationState

object CreateNewPassword {
    const val routeName = "CreateNewPassword"
}

fun NavGraphBuilder.routeCreateNewPassword(
    state: ApplicationState,
) {
    composable(CreateNewPassword.routeName) {
        ScreenCreateNewPassword(
            state
        )
    }
}

@Composable
internal fun ScreenCreateNewPassword(appState: ApplicationState) = UIWrapper<CreateNewPasswordViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState()
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
                text = stringResource(id = R.string.title_create_new_password),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
            )
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = stringResource(id = R.string.detail_create_new_password),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF616161),
            )
            FormInputPassword(
                value = state.password,
                label = stringResource(id = R.string.label_input_password_create_new_password),
                placeholder = stringResource(id = R.string.placeholder_password_create_new_password),
                error = state.isInputPasswordError,
                errorMessage = state.errorInputPassword,
                onChange = {
                    commit { copy(password = it) }
                }
            )
            FormInputPassword(
                value = state.confirmPassword,
                label = stringResource(id = R.string.label_confirm_password_create_new_password),
                placeholder = stringResource(id = R.string.placeholder_password_create_new_password),
                error = state.isConfirmPasswordError,
                errorMessage = state.errorConfirmPassword,
                onChange = {
                    commit { copy(confirmPassword = it) }
                }
            )
        }

        ButtonPrimary(
            text = stringResource(id = R.string.text_button_save_new_password),
            enabled = (state.password.isNotEmpty() && state.confirmPassword.isNotEmpty()),
            onClick = { dispatch(CreateNewPasswordEvent.Submit) }
        )

    }

}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenCreateNewPassword(
            appState = rememberApplicationState()
        )
    }
}

