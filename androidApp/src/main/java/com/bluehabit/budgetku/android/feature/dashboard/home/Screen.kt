/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
internal fun ScreenHome(
    modifier: Modifier = Modifier,
    onClick:()->Unit={}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = onClick) {
            Text(text = "INI")
        }
    }
}

@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp {
        ScreenHome()
    }

}