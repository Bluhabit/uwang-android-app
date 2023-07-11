/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.eureka.feature.dashboard.home.DashboardState
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey200
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey500

@Composable
fun ScreenReport(
    state: DashboardState = DashboardState(),
) {
    LazyColumn(content = {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Bagaimana alokasi Keuanganmu?",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(
                        horizontal = 20.dp
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        FilterChip(
                            selected = true,
                            enabled = true,
                            onClick = {
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            )
                        ) {
                            Text(
                                text = "Pemasukkan"
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        FilterChip(
                            selected = false,
                            enabled = true,
                            onClick = {
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            ),
                            border = BorderStroke(
                                width = 1.dp,
                                color = Grey300
                            )
                        ) {
                            Text(
                                text = "Pemasukkan"
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
        item {
            Text(
                text = "Profil Arus Kas Kamu",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
        }
    })
}

@Preview
@Composable
fun PreviewScreenReport() {
    BaseMainApp {
        ScreenReport()
    }
}