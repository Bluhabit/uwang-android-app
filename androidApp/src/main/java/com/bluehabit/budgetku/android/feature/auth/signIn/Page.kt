/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.components.button.ButtonGoogle
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.components.input.FormInput
import com.bluehabit.budgetku.android.components.input.FormInputPassword
import com.bluehabit.budgetku.android.feature.auth.resetPassword.ResetPassword
import com.bluehabit.budgetku.android.feature.auth.signUp.SignUp
import com.bluehabit.budgetku.android.rememberApplicationState
import com.bluehabit.budgetku.android.ui.Blue800
import com.bluehabit.budgetku.android.ui.Grey500

object SignIn {
    const val routeName = "SignIn"
}

fun NavGraphBuilder.routeSignIn(
    state: ApplicationState,
) {
    composable(SignIn.routeName) {
        ScreenSignIn(
            state
        )
    }
}

@Composable
internal fun ScreenSignIn(appState: ApplicationState) = UIWrapper<SignInViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = GoogleAuthContract(),
        onResult = { dispatch(SignInEvent.SignInWithGoogle(it)) }
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 55.dp, top = 48.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo_budgetku_full),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .height(36.dp)
                            .width(170.dp)
                    )
                }

            }
            item {
                Text(
                    text = stringResource(R.string.label_title_login),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp, top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.label_have_account),
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        text = stringResource(R.string.label_register_here),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = Blue800,
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = {
                                navigateSingleTop(SignUp.routeName)
                            }
                        )
                    )
                }
            }
            item {
                FormInput(
                    placeholder = stringResource(R.string.input_your_email),
                    label = stringResource(R.string.email),
                    value = state.email,
                    error = state.emailIsError,
                    errorMessage = stringResource(R.string.email_not_valid),
                    onChange = {
                        dispatch(SignInEvent.OnEmailChange(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
                FormInputPassword(
                    placeholder = stringResource(R.string.input_your_password),
                    label = stringResource(R.string.password),
                    value = state.password,
                    error = state.passwordIsError,
                    errorMessage = stringResource(R.string.password_required),
                    onChange = {
                        dispatch(SignInEvent.OnPasswordChange(it))
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            dispatch(SignInEvent.SignInWithEmail)
                        }
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.forgot_password),
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = Blue800,
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = {
                                navigateSingleTop(ResetPassword.routeName)
                            }
                        )
                    )
                }
            }
            item {
                ButtonPrimary(text = stringResource(R.string.login), onClick = {
                    dispatch(SignInEvent.SignInWithEmail)
                })
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.or),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Grey500
                )
                Spacer(modifier = Modifier.height(24.dp))
                ButtonGoogle(
                    text = stringResource(R.string.sign_in_with_google)
                )
            }
        }, contentPadding = PaddingValues(horizontal = 21.dp)
    )

}


@Preview
@Composable
fun PreviewScreenSignIn() {
    BaseMainApp {
        ScreenSignIn(
            appState = rememberApplicationState()
        )
    }
}

