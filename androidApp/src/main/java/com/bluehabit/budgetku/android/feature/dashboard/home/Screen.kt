/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
internal fun ScreenHome(
    modifier: Modifier = Modifier,
) {
    Column {

    }
}

@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp {
        _,_->
        ScreenHome()
    }

}