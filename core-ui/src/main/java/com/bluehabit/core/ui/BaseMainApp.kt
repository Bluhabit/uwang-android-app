/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bluehabit.core.ui.theme.BudgetKuTheme

@Composable
fun BaseMainApp(
    controller: UIController = rememberUIController(),
    content: @Composable (appState: UIController) -> Unit = { }
) {
    BudgetKuTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface
        ) {
            content.invoke(controller)
        }
    }
}