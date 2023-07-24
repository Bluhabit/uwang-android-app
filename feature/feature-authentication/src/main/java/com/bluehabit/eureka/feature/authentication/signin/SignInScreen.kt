/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract

@Navigation(
    route = "sign-in",
    viewModel = SignInViewModel::class
)
@Composable
fun SignInScreen(
    uiContract: UIContract<SignInState, SignInAction>
) = UIWrapper(uiContract = uiContract) {
    UseEffect(
        key = state.effect,
        onDispose = { copy(effect = SignInEffect.Nothing) },
        block = {
            when (this) {
                SignInEffect.NavigateToHome -> {
                    navigator.navigate("home")
                }

                SignInEffect.Nothing -> Unit
            }
        }
    )

    Column {
        Text(text = "Login")

        TextField(
            value = state.email,
            onValueChange = {
                commit { copy(email = it) }
            }
        )

        TextField(
            value = state.password,
            onValueChange = {
                commit { copy(password = it) }
            }
        )

        Button(onClick = {
            dispatch(SignInAction.Submit)
        }) {
            Text(
                text = "Login"
            )
        }
    }

}