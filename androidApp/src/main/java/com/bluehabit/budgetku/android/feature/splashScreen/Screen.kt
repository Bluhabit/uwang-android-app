package com.bluehabit.budgetku.android.feature.splashScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.BaseMainApp

@Composable
internal fun ScreenSplash(
    modifier: Modifier = Modifier,
) {
    Column {

    }
}

@Preview
@Composable
fun PreviewScreenSplash() {
    BaseMainApp { _, _ ->
        ScreenSplash()
    }
}