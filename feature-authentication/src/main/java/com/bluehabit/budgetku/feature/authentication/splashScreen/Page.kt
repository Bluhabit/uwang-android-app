/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.UIWrapper
import com.bluehabit.core.ui.UIWrapperListener
import com.bluehabit.core.ui.rememberUIController

object Splash {
    const val routeName = "Splash"
}


@Composable
fun ScreenSplash(
    invoker: UIWrapperListener<SplashState, SplashEvent>
) = UIWrapper(invoker = invoker) {
    LaunchedEffect(key1 = this, block = {
        dispatch(SplashEvent.CheckSession)
    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = com.bluehabit.core.ui.R.drawable.ic_logo_budgetku_full),
            contentDescription = "Logo App",
            modifier = Modifier
                .height(104.dp)
                .width(140.dp)
        )
    }
}


@Preview
@Composable
fun PreviewScreenSplash() {
    BaseScreen {
        ScreenSplash(
            invoker = UIWrapperListener(
                controller = rememberUIController(),
                state = SplashState()
            )
        )
    }
}