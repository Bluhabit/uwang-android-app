/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signUp

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.TextWithAction
import com.bluehabit.budgetku.android.feature.auth.signIn.SignIn
import com.bluehabit.budgetku.android.rememberApplicationState

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
) =UIWrapper<SignUpViewModel>(appState = appState) {
        val state by uiState.collectAsState()

        val launcher = rememberLauncherForActivityResult(
            contract = GoogleAuthContract(),
            onResult = { dispatch(SignUpEvent.SignUpWithGoogle(it)) }
        )



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column {
                TextField(
                    value = state.fullName,
                    onValueChange = {
                        dispatch(SignUpEvent.SetFullName(it))
                    },
                    placeholder = {
                        Text(text = "Full Name")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = state.email,
                    onValueChange = {
                        dispatch(SignUpEvent.SetEmail(it))
                    },
                    placeholder = {
                        Text(text = "Email")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = state.password,
                    onValueChange = {
                        dispatch(SignUpEvent.SetPassword(it))
                    },
                    placeholder = {
                        Text(text = "Password")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Button(
                    onClick = {
                        dispatch(SignUpEvent.SignUpWithEmail)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Sign In")
                }

                Button(
                    onClick = { launcher.launch(1) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Continue With Google")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextWithAction(
                labels = listOf(
                    AnnotationTextItem.Text("Sudah punya akun?"),
                    AnnotationTextItem.Button("Masuk disni")
                ),
                onTextClick = {
                    if (it == 1) {
                        appState.navigateAndReplaceAll(SignIn.routeName)
                    }
                }
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