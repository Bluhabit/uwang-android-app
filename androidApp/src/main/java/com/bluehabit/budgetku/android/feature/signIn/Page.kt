/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.signIn

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
import com.bluehabit.budgetku.android.base.extensions.navigate
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.TextWithAction
import com.bluehabit.budgetku.android.feature.signUp.SignUp
import com.bluehabit.budgetku.android.rememberApplicationState

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
internal fun ScreenSignIn(
    appState: ApplicationState
) {
    UIWrapper<SignInState,SignInEvent, SignInViewModel>(
        appState = appState
    ) { (email,password) ->

        val launcher = rememberLauncherForActivityResult(
            contract = GoogleAuthContract(),
            onResult = {sendEvent(SignInEvent.SignInWithGoogle(it))}
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
                    value = email,
                    onValueChange ={sendEvent(SignInEvent.SetEmail(it))},
                    placeholder = {
                        Text(text = "Email")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = password,
                    onValueChange = {sendEvent(SignInEvent.SetPassword(it))},
                    placeholder = {
                        Text(text = "Password")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Button(
                    onClick = {sendEvent(SignInEvent.SignInWithEmail)},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Sign In")
                }

                Button(
                    onClick = {
                        launcher.launch(1)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Continue With Google")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextWithAction(
                labels = listOf(
                    AnnotationTextItem.Text("Belum punya akun?"),
                    AnnotationTextItem.Button("Daftar disni")
                ),
                onTextClick = {
                    if (it == 1) {
                        appState.navigate(SignUp.routeName)
                    }
                }
            )

        }

    }

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

