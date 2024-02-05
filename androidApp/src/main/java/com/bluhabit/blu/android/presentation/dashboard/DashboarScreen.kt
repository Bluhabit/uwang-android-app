/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.components.button.ButtonPrimary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun DashboardScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<DashboardState> = flowOf(),
    effectFlow: Flow<DashboardEffect> = flowOf(),
    onAction: (DashboardAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = DashboardState())
    val effect by effectFlow.collectAsStateWithLifecycle(initialValue = DashboardEffect.None)

    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            DashboardEffect.Exit -> {
                navHostController.navigateUp()
            }

            DashboardEffect.None -> Unit
        }
    })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = ""
        )
        Text(text = "Ini adalah halaman utama.")
        ButtonPrimary(
            text = "Personalisasi akun",
            onClick = {
                navHostController.navigate("personalize")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonPrimary(
            text = "Keluar",
            onClick = {
                onAction(DashboardAction.SignOut)
            }
        )

    }

}