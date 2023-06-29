/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.signUp

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.BaseMainApp
import app.trian.mvi.ui.BaseScreen
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import com.bluehabit.budgetku.feature.authentication.R
import com.bluehabit.core.ui.components.AnnotationTextItem
import com.bluehabit.core.ui.components.IconWithAction
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetConfirmationEmail
import com.bluehabit.core.ui.components.button.ButtonGoogle
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.FormInput



@Composable
fun SignUpScreen(
    uiContract: UIContract<SignUpState,SignUpIntent,SignUpAction>
) = UIWrapper(
    uiContract
) {

//    val launcher = rememberLauncherForActivityResult(
//        contract = GoogleAuthContract(),
//        onResult = { dispatch(SignUpEvent.SignUpWithGoogle(it)) }
//    )


    BaseScreen(
        bottomSheet = {
            BottomSheetConfirmationEmail(
                email = state.email,
                onConfirm = {
                    //hideBottomSheet()
                    // navigateAndReplaceAll(EmailVerification.routeName)
                }
            )
        }
    ) {
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
                               // navigateUp()
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
                           // hideKeyboard()
                            dispatch(SignUpAction.Submit)
                        }
                    ),
                    error = !state.isEmailValid,
                    errorMessage = state.errorMessage
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
                               // hideKeyboard()
                                //  navigateSingleTop(ResetPassword.routeName)
                            }
                        )
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                ButtonPrimary(
                    text = stringResource(R.string.text_button_register_signup),
                    onClick = {
                        dispatch(SignUpAction.Submit)
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
                    onClick = { },
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
}


@Preview
@Composable
fun PreviewSignUpScreen() {
    BaseMainApp {
        SignUpScreen()
    }

}