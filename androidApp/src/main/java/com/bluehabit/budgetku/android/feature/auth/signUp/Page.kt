/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signUp

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.button.ButtonGoogle
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.components.input.FormInput
import com.bluehabit.budgetku.android.components.IconWithAction
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetConfirmationEmail
import com.bluehabit.budgetku.android.feature.auth.emailVerification.EmailVerification
import com.bluehabit.budgetku.android.feature.auth.resetPassword.ResetPassword
import com.bluehabit.budgetku.android.rememberApplicationState
import com.bluehabit.budgetku.android.ui.Grey700


object SignUp {
    const val routeName = "SignUp"
}

fun NavGraphBuilder.routeSignUp(
    state: ApplicationState,
) {
    composable(SignUp.routeName) {
        ScreenSignUp(appState = state)
    }
}


@Composable
internal fun ScreenSignUp(
    appState: ApplicationState
) = UIWrapper<SignUpViewModel>(appState = appState) {
    val state by uiState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = GoogleAuthContract(),
        onResult = { dispatch(SignUpEvent.SignUpWithGoogle(it)) }
    )

    with(appState) {
        setupBottomSheet {
            BottomSheetConfirmationEmail(
                email = state.email,
                onConfirm = {
                    hideBottomSheet()
                    navigateAndReplaceAll(EmailVerification.routeName)
                }
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 32.dp,
            ),
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Column() {
            Image(
                painter = painterResource(R.drawable.logo_budgetku_full),
                contentDescription = stringResource(R.string.logo_budgetku_signup),
                modifier = Modifier.size(169.dp)
            )

            Text(
                text = stringResource(R.string.label_register_newaccount_signup),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(

                    text = stringResource(R.string.label_haveanyaccount_signup),
                    color = Grey700,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = stringResource(R.string.text_button_signinhere_signup),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            navigateUp()
                        }
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            FormInput(
                value = state.email,
                label = stringResource(id = R.string.label_input_email_signup),
                placeholder = stringResource(R.string.placeholder_input_email_signup),
                onChange = {
                    commit {
                        copy(
                            email = it
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        hideKeyboard()
                        dispatch(SignUpEvent.Submit)
                    }
                ),
                error=!state.isEmailValid,
                errorMessage=state.errorMessage
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.text_button_forgetpassword_signup),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            hideKeyboard()
                            navigateSingleTop(ResetPassword.routeName)
                        }
                    )
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            ButtonPrimary(
                text = stringResource(R.string.text_button_register_signup),
                onClick = {
                    dispatch(SignUpEvent.Submit)
                },
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.label_or_signup),
                    color = Grey700,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonGoogle(
                text = stringResource(R.string.text_button_signin_with_google_signup),
                onClick = {  },
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        IconWithAction(
            icon = {
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
            },
            labels = listOf(
                AnnotationTextItem.Text(stringResource(R.string.label_agreement_signup)),
                AnnotationTextItem.Button(stringResource(R.string.textbutton_privacyandpolice_signup)),
                AnnotationTextItem.Text(stringResource(R.string.label_serta_signup)),
                AnnotationTextItem.Button(stringResource(R.string.textbutton_termsprivated_signup))
            )
        )

    }
}


@Preview
@Composable
fun PreviewScreenSignUp() {
    BaseMainApp {
        ScreenSignUp(rememberApplicationState())
    }

}