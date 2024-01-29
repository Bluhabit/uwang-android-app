/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenFrameOnboarding(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit = {},
    indicator: @Composable () -> Unit = {},
    mid: @Composable () -> Unit = {},
    bottom: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

       Column {
           Column(
               modifier = modifier.padding(vertical = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
           ) {
               Box(
                   modifier = modifier
                       .fillMaxWidth()
                       .padding(start = 16.dp,end = 16.dp, top = 20.dp, bottom = 0.dp),
                   contentAlignment = Alignment.Center
               ) {
                   indicator()
               }
           }
           header()
           mid()
       }
        bottom()
    }
}