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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.bluehabit.budgetku.android.base.extensions.navigateAndReplaceAll
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.components.IconWithAction
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
        modifier = Modifier.fillMaxSize()
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp ,
                top =  20.dp,
            ),
    ) {
        Image(
            painter = painterResource(R.drawable.logo_budgetku_full),
            contentDescription = "Logo Budgetku Full",
                    modifier = Modifier.size(169.dp)
        )

        Text(
            text = "Daftar Akun Baru",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Teks "Sudah punya akun?"
            Text(

                text = "Sudah punya akun?",
                color = Color.Black,
                style = MaterialTheme.typography.subtitle1
            )
            // Teks "Login disini" yang bisa diklik
            Text(
                text = "Login disini",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Aksi yang dilakukan ketika teks "Login disini" diklik
                    // Contoh: Navigasi ke halaman login
                }
            )
        }

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
                FormInput(
                    value = state.fullName,
                    label = "Email",
                    placeholder = "Masukkan Email Kamu",
                    onChange = {
                        commit {
                            copy(
                                fullName = it
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                )
            Row(
                verticalAlignment = Alignment.End,
            ) {
                Text(
                    text = "Lupa Password?",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        // Aksi yang dilakukan ketika teks "Login disini" diklik
                        // Contoh: Navigasi ke halaman login
                    }
                )
            }

                    Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        dispatch(SignUpEvent.SignUpWithEmail)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar")
                }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Atau",
                    color = MaterialTheme.colors.,
                    style = MaterialTheme.typography.subtitle1
                )
            }
                Button(
                    onClick = { launcher.launch(1) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar dengan Google")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        IconWithAction(
            icon={
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
            },
            labels = listOf(
                AnnotationTextItem.Text("Dengan mendaftar, saya menyetujui"),
                AnnotationTextItem.Button("Syarat dan Ketentuan"),
                AnnotationTextItem.Text("serta"),
                AnnotationTextItem.Button("Kebijakan Privasi")
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