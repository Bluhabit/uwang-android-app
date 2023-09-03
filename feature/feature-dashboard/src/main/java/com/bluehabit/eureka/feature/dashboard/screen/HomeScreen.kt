/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.eureka.feature.dashboard.DashboardState

@Composable
fun HomeScreen(
    state: DashboardState
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = ""
            )
            Column {
                Text(
                    text = "Selamat datang kembali",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    fontWeight = FontWeight.W500,
                    color = Gray600
                )
                Text(
                    text = state.fullName,
                    style = MaterialTheme.typography.body2.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    fontWeight = FontWeight.W400,
                    color = Gray600,
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(DashboardState(fullName = "Olivia Rhye"))
}