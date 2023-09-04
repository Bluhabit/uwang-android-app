/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.SearchBar
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Primary700
import com.bluehabit.eureka.feature.dashboard.DashboardState

@Composable
fun ListTaskScreen(
    state: DashboardState
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(top = 12.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Tugas Saya",
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    fontWeight = FontWeight.W500,
                    color = Gray600
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = stringResource(id = R.string.content_description_logo)
                    )
                }
            }
        }
        item {
            SearchBar(
                value = state.inputSearch,
                placeholder = "Cari tugas atau proyek anda",
                onChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
        item {
            TabRow(
                selectedTabIndex = 0,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Primary700
            ) {
                state.tabsListTask.forEachIndexed { index, itemTabListTask ->
                    Tab(selected = 0 == index, onClick = { /*TODO*/ }) {
                        Text(
                            text = itemTabListTask.title,
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun ListTaskScreenPreview() {
    GaweanTheme {
        ListTaskScreen(DashboardState(fullName = "Olivia Rhye"))
    }
}