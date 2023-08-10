/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstructionReset(
    openEmail:() -> Unit = {},
    tryAgain:() -> Unit = {},
){
    Column(
        modifier = Modifier
            .padding(
                vertical = 28.dp,
                horizontal = 26.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}