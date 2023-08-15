/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.extensions.from
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.core.ui.theme.Primary25
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.eureka.feature.authentication.auth.screen.ScreenSignIn
import com.bluehabit.eureka.feature.authentication.auth.screen.ScreenSignUp

@Navigation(
    route = Routes.SignIn.routeName,
    viewModel = AuthViewModel::class
)
@Composable
fun AuthScreen(
    uiContract: UIContract<AuthState, AuthAction>,
) = UIWrapper(uiContract = uiContract) {
    val context = LocalContext.current
    UseEffect<AuthEffect>(
        commit = { copy(effect = AuthEffect.Nothing) },
        onEffect = {
            when (this) {
                AuthEffect.Nothing -> Unit
                is AuthEffect.ShowDialog -> {
                    Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()

                }
            }
        }
    )
    LaunchedEffect(key1 = this, block = {
        dispatch(AuthAction.CheckSession)
    })
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(24.dp.from(context = context), alignment = Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.bluehabit.core.ui.R.drawable.gawean_logo),
                    contentDescription = "Logo Gawean"
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Selamat datang di Gawean",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.W600,
                        lineHeight = 30.sp,
                        color = Gray900,
                    )
                    Text(
                        text = "Daftar atau masuk agar bisa atur tugas lebih mudah dan selalu produktif.",
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.W400,
                        color = Gray900,
                        textAlign = TextAlign.Center
                    )
                }
                TabRow(
                    selectedTabIndex = state.selectedTab,
                    backgroundColor = Color.White,
                    contentColor = Primary600
                ) {
                    state.tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = state.selectedTab == index,
                            onClick = {
                                commit { copy(selectedTab = index) }
                            },
                            text = { Text(text = title) }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .background(Primary25)
                    .fillMaxSize()
                    .padding(
                        vertical = 16.dp
                    )
            ) {
                when (state.selectedTab) {
                    0 -> ScreenSignIn()
                    1 -> ScreenSignUp()
                }
            }
        }

    }

}

@Preview(widthDp = 384, heightDp = 854, backgroundColor = 0xFFFCFAFF)
@Composable
fun PreviewSignInScreen() {
    AuthScreen(
        uiContract = UIContract(
            controller = rememberUIController(),
            state = AuthState()
        )
    )
}