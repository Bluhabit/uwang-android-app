/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
internal fun ScreenProfile(
    modifier: Modifier = Modifier,
    onClick:()->Unit={}
) {
    Column {

        Button(onClick =onClick) {
            Text(text = "Klik Coba")
        }
    }
}

@Preview
@Composable
fun PreviewScreenProfile() {
    BaseMainApp {
        ScreenProfile()
    }

}