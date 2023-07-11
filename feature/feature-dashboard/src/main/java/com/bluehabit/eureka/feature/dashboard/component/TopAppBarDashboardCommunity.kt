/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey500
import com.bluehabit.core.ui.theme.Grey900

@Composable
fun TopAppBarDashboardCommunity(
    selected: Int = 0,
    onCreatePost: () -> Unit = {},
    onSelectTab: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_title_dashboard_community),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Grey900
            )
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit_pencil),
                    contentDescription = "",
                    tint = Grey900,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = onCreatePost
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "",
                    tint = Grey900,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {}
                    )
                )
            }
        }
        TabRow(
            selectedTabIndex = selected,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary
        ) {
            Tab(
                selected = true,
                onClick = { onSelectTab(0) },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_feed))
                }
            )
            Tab(
                selected = false,
                onClick = { onSelectTab(1) },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_article))
                }
            )
            Tab(
                selected = false,
                onClick = {
                    onSelectTab(2)
                },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_academy))
                }
            )
        }
    }

}