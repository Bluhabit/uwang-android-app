/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import com.bluehabit.core.ui.routes.Routes

@Navigation(
    route = Routes.SignIn.routeName,
    viewModel = SignInViewModel::class
)
@Composable
fun SignInScreen(
    uiContract: UIContract<SignInState, SignInAction>
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    UseEffect<SignInEffect>(
        commit = { copy(effect = SignInEffect.Nothing) },
        onEffect = {
            when (this) {
                SignInEffect.Nothing -> Unit
                is SignInEffect.ShowDialog -> {
                    Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()

                }
            }
        }
    )
    LaunchedEffect(key1 = this, block = {
        dispatch(SignInAction.CheckSession)
    })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {}
}