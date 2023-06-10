/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccountSaving.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.components.input.FormInput

@Composable
fun ScreenInputGoalsName(
    value: String = "",
    onChange: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp
            )
    ) {
        Text(
            text = "Apa nama tujuan kamu?",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormInput(
            value=value,
            placeholder = "Masukkan nama tujuan kamu",
            onChange = onChange
        )

    }

}

@Preview
@Composable
fun PreviewScreenInputGoalsName() {
    BaseMainApp {
        ScreenInputGoalsName()
    }
}