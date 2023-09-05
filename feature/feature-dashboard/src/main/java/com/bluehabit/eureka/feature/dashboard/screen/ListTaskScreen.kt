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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.SearchBar
import com.bluehabit.core.ui.components.item.itemTask.ItemTask
import com.bluehabit.core.ui.components.item.itemTask.ProgressItemTask
import com.bluehabit.core.ui.components.screen.EmptyListPage
import com.bluehabit.core.ui.ext.toColor
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700
import com.bluehabit.eureka.feature.dashboard.DashboardState
import com.bluehabit.eureka.feature.dashboard.model.ItemTabListTask

@Composable
fun ListTaskScreen(
    state: DashboardState,
    onClickNotification: () -> Unit = {},
    onTabSelected: (Int, ItemTabListTask) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
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
                IconButton(onClick = onClickNotification) {
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
                selectedTabIndex = state.selectedTabIndex,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Primary700,
                modifier = Modifier
                    .padding(horizontal = 18.dp)
            ) {
                state.tabsListTask.forEachIndexed { index, tab ->
                    Tab(
                        text = {
                            Text(
                                text = tab.title,
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 13.sp.from(context = context)
                                ),
                                fontWeight = FontWeight.W500,
                                color = Primary600,
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = state.selectedTabIndex == index,
                        onClick = {
                            onTabSelected(index, tab)
                        },
                    )
                }
            }
        }
        state.listAllTask.forEach { (key, data) ->
            stickyHeader {
                Text(text = key)
            }
            itemsIndexed(data) { _, task ->
                if (!task.subtasks.isNullOrEmpty()) {
                    ProgressItemTask(
                        title = task.name.orEmpty(),
                        startDate = task.taskStart.orEmpty(),
                        dueDate = task.taskEnd.orEmpty(),
                        subTaskCount = task.subtasks!!.size,
                        priority = task.priority?.name ?: "none",
                        iconPriorityTint = task.priority?.color?.toColor(default = Color(0xFF98A2B3))!!
                    )
                } else {
                    ItemTask(
                        title = task.name.orEmpty(),
                        date = "${task.taskStart} - ${task.taskEnd}",
                        priority = task.priority?.name ?: "none",
                        iconPriorityTint = task.priority?.color?.toColor(default = Color(0xFF98A2B3))!!
                    )
                }
            }
        }
        if (
            (state.selectedTabIndex == 0 && state.listAllTask.isEmpty()) || (state.selectedTabIndex > 0 && state.listTask.isEmpty())
        ) {
            item {
                EmptyListPage(
                    label = stringResource(id = R.string.text_label_empty_list_task),
                    icon = {
                        Image(
                            painterResource(id = R.drawable.empty_list_task),
                            contentDescription = "",
                            modifier = Modifier
                                .width(272.dp.from(context = context))
                                .height(197.dp.from(context = context)),
                        )
                    },
                    title = stringResource(id = R.string.text_title_empty_list),
                    message = stringResource(id = R.string.text_placeholder_empty_list),

                    )
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