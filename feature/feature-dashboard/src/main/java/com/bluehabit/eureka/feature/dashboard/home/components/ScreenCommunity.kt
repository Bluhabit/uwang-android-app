/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey50
import com.bluehabit.eureka.feature.dashboard.home.DashboardState

@Composable
fun ScreenCommunity(
    state: DashboardState = DashboardState(),
    onSelectCategory:(Int)->Unit={}
) {
    LazyColumn(
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .background(Grey50)
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cari postingan"
                        )
                    }

                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(
                            fraction = 0.9f
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        items(state.categories.size) {
                            FilterChip(
                                selected = state.selectedCategory == it,
                                onClick = {
                                    onSelectCategory(it)
                                },
                            ) {
                                Text(text = state.categories[it])
                            }
                        }
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_circle),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            )
                    )

                }
            }
        }
    )
}