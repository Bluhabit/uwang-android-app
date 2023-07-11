/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.changePassword

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.BaseScreen
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.FormInputPassword
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes

@Navigation(
    route = Routes.ChangePassword.routeName,
    group = AuthenticationConstants.parentRoute,
    viewModel = ChangePasswordViewModel::class
)
@Composable
fun ChangePasswordScreen(
    uiContract: UIContract<ChangePasswordState,ChangePasswordIntent,ChangePasswordAction>
) = UIWrapper(
    uiContract
) {

    BaseScreen(
        topAppBar = {
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
                                navigator.navigateUp()
                            }
                    )
                },
                backgroundColor = Color.White,
                title = {
                    Text(text = "")
                },
            )
        },
        bottomBar = {
            ButtonPrimary(
                text = stringResource(id = R.string.text_button_save_change_password),
                enabled = state.isButtonEnabled,
                onClick = { dispatch(ChangePasswordAction.ChangeToNewPassword(state.newPassword)) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 24.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.title_change_password),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121),
                )
                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = stringResource(id = R.string.subtitle_change_password),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF616161),
                )
                FormInputPassword(
                    value = state.oldPassword,
                    label = stringResource(id = R.string.label_input_old_password_change_password),
                    placeholder = stringResource(id = R.string.placeholder_old_password_change_password),
                    error = state.isInputOldPasswordError,
                    errorMessage = state.errorInputOldPassword,
                    onChange = {
                        commit { copy(oldPassword = it) }
                        dispatch(ChangePasswordAction.ValidateOldPassword)
                    }
                )
                FormInputPassword(
                    value = state.newPassword,
                    label = stringResource(id = R.string.label_input_new_password_change_password),
                    placeholder = stringResource(id = R.string.placeholder_new_password_change_password),
                    error = state.isInputNewPasswordError,
                    errorMessage = state.errorInputNewPassword,
                    onChange = {
                        commit { copy(newPassword = it) }
                        dispatch(ChangePasswordAction.ValidateNewPassword)
                        dispatch(ChangePasswordAction.ValidateConfirmNewPassword)
                        dispatch(ChangePasswordAction.HandleButtonSaveChanges)
                    }
                )
                FormInputPassword(
                    value = state.confirmNewPassword,
                    label = stringResource(id = R.string.label_input_old_password_change_password),
                    placeholder = stringResource(id = R.string.placeholder_confirm_new_password_change_password),
                    error = state.isConfirmNewPasswordError,
                    errorMessage = state.errorConfirmNewPassword,
                    onChange = {
                        commit { copy(confirmNewPassword = it) }
                        dispatch(ChangePasswordAction.ValidateNewPassword)
                        dispatch(ChangePasswordAction.ValidateConfirmNewPassword)
                        dispatch(ChangePasswordAction.HandleButtonSaveChanges)
                    }
                )
            }


        }
    }


}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ChangePasswordScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = ChangePasswordState()
            )
        )
    }
}