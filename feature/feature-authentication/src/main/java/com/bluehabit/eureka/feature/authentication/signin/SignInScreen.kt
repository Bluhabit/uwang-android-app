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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.input.InputTextPrimary
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.authentication.R

@Navigation(
    route = Routes.SignIn.routeName,
    viewModel = SignInViewModel::class
)
@Composable
fun SignInScreen(
    uiContract: UIContract<SignInState, SignInAction>,
    isEmailValid:Boolean = true,
    isPasswordValid:Boolean = true,
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
            .background(MaterialTheme.colors.surface)
            .padding(
                vertical = 24.dp,
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Top)
        ){
            var emailInput by remember {
                mutableStateOf("")
            }
            var passwordInput by remember {
                mutableStateOf("")
            }
            InputTextPrimary(
                label = "Email",
                value = emailInput,
                onChange = { emailInput = it},
                eror = !isEmailValid,
                enable = true,
            )
            InputTextPrimary(
                label = "Password",
                value = passwordInput,
                onChange = { passwordInput = it},
                eror = !isPasswordValid,
                enable = true,
                )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    ) {
                    val isChecked = remember { mutableStateOf(false) }

                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        enabled = true,
                        colors = CheckboxDefaults.colors(Primary600)
                    )
                    Text(
                        text = stringResource(R.string.remember_me),
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = W500,
                        color = Gray700,
                    )
                }
                Text(
                    text = stringResource(R.string.forget_password),
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = W500,
                    color = Primary600,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp),

            ) {
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                        horizontal = 18.dp,
                        vertical = 10.dp
                        ),
                    text = "Masuk"
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 384, heightDp = 854)
fun PreviewSignInScreen(){
    SignInScreen(uiContract =UIContract(
        controller = rememberUIController(),
        state=SignInState()
    ))
}