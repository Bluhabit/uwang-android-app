/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.signIn

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.R
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.contract.GoogleAuthContract
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.rememberApplicationState
import com.bluehabit.budgetku.android.ui.Blue800

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

    LazyColumn(content = {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 55.dp),
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
                text = "Daftar Akun Baru",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sudah punya akun?",
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = "Login Disini",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Blue800
                )
            }
        }
        item {
            Text(
                text = "Email",
                style = MaterialTheme.typography.subtitle2
            )
            FormInput(
                placeholder = "Masukan email kamu"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Lupa Password?",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Blue800
                )
            }
        }
        item {
            ButtonPrimary(text = "Daftar")
        }
    })

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

