/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bluhabit.blu.android.presentation.home.HomeAction
import com.bluhabit.blu.android.presentation.home.HomeState

@Composable
fun NotificationScreen(
    paddingValues: PaddingValues = PaddingValues(),
    state: HomeState = HomeState(),
    onAction: (HomeAction) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Notification Screen")
    }
}