/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.SearchBar
import com.bluehabit.core.ui.components.item.itemTask.ItemTask
import com.bluehabit.core.ui.components.item.itemTask.ProgressItemTask
import com.bluehabit.core.ui.ext.toColor
import com.bluehabit.core.ui.theme.Gray600
import com.bluehabit.core.ui.theme.Primary600
import com.bluehabit.core.ui.theme.Primary700
import com.bluehabit.eureka.data.task.datasource.remote.response.ChannelResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.PriorityTaskResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.TaskResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.UserInfoResponse
import com.bluehabit.eureka.data.task.datasource.remote.response.UserResponse
import com.bluehabit.eureka.feature.dashboard.DashboardState
import com.bluehabit.eureka.feature.dashboard.model.ItemTabListTask

@Composable
fun HomeScreen(
    state: DashboardState,
    onNotificationIconClick: () -> Unit = {},
    onSearchChanged: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onTaskCheckChanged: (taskId: String, newValue: Boolean) -> Unit = { _, _ -> },
    onTaskClicked: () -> Unit = {},
    onTabSelected: (Int, ItemTabListTask) -> Unit = { _, _ -> },
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(top = 12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "",
                )
                Column {
                    Text(
                        text = stringResource(id = R.string.text_welcome_back),
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onNotificationIconClick) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = stringResource(id = R.string.content_description_logo)
                    )
                }
            }
        }
        item {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeholder = stringResource(id = R.string.text_placeholder_search_bar),
                value = state.inputSearch,
                onChange = { onSearchChanged(it) },
                onSearch = { onSearch(it) }
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.text_favorite_task),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                fontWeight = FontWeight.W500,
                color = Gray600,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        }
        if (state.favoriteItemTask.isNotEmpty()) {
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(state.favoriteItemTask) { task ->
                        ItemTask(
                            title = task.name.orEmpty(),
                            date = "${task.taskStart} - ${task.taskEnd}",
                            priority = task.priority?.name ?: "none",
                            iconPriorityTint = task.priority?.color?.toColor(default = Color(0xFF98A2B3))!!
                        )
                    }
                }
            }
        }
        item {
            TabRow(
                selectedTabIndex = state.selectedTabIndex,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Primary700,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                state.tabsHome.forEachIndexed { index, tab ->
                    Tab(
                        text = {
                            Text(
                                text = tab.title,
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                fontWeight = FontWeight.W500,
                                color = Primary600,
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
        if (state.listAllTaskHome.isNotEmpty()) {
            state.listAllTaskHome.forEach { (key, data) ->
                stickyHeader {
                    Text(
                        text = key,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        fontWeight = FontWeight.W500,
                        color = Gray600,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )
                }
                itemsIndexed(data) { _, task ->
                    if (!task.subtasks.isNullOrEmpty()) {
                        ProgressItemTask(
                            title = task.name.orEmpty(),
                            startDate = task.taskStart.orEmpty(),
                            dueDate = task.taskEnd.orEmpty(),
                            subTaskCount = task.subtasks!!.size,
                            priority = task.priority?.name ?: "none",
                            iconPriorityTint = task.priority?.color?.toColor(default = Color(0xFF98A2B3))!!,
//                            TODO
//                            checked = task.status,
                            onCheckedChange = {
                                onTaskCheckChanged(task.id, it)
                            },
                            onItemClicked = onTaskClicked,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        )
                    } else {
                        ItemTask(
                            title = task.name.orEmpty(),
                            date = "${task.taskStart} - ${task.taskEnd}",
                            iconPriorityTint = task.priority?.color?.toColor(default = Color(0xFF98A2B3))!!,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                        )
                    }
                }
            }
        } else {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_task_image),
                        contentDescription = stringResource(id = R.string.empty_task_image),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val userResponse1 = UserResponse(
        id = "USER_001",
        email = "user1@example.com",
        authProvider = "email",
        status = "active",
        userInfo = listOf(
            UserInfoResponse(
                id = "USER_001_INFO_001",
                userId = "USER_001",
                key = "name",
                value = "John Doe",
                createdAt = "2023-09-03T10:00:00Z",
                updatedAt = "2023-09-03T10:00:00Z",
                deleted = false
            ),
            UserInfoResponse(
                id = "USER_001_INFO_002",
                userId = "USER_001",
                key = "profile_url",
                value = "https://www.rri.res.in/sites/default/files/2022-09/Abhisek%20Tamang.jpg",
                createdAt = "2023-09-03T10:00:00Z",
                updatedAt = "2023-09-03T10:00:00Z",
                deleted = false
            )
        ),
        createdAt = "2023-09-03T10:00:00Z",
        updatedAt = "2023-09-03T10:00:00Z",
        deleted = false
    )
    val userResponse2 = UserResponse(
        id = "USER_002",
        email = "user2@example.com",
        authProvider = "google",
        status = "active",
        userInfo = listOf(
            UserInfoResponse(
                id = "USER_002_INFO_001",
                userId = "USER_002",
                key = "name",
                value = "John Doe",
                createdAt = "2023-09-03T10:00:00Z",
                updatedAt = "2023-09-03T10:00:00Z",
                deleted = false
            ),
            UserInfoResponse(
                id = "USER_002_INFO_002",
                userId = "USER_002",
                key = "profile_url",
                value = "https://www.rri.res.in/sites/default/files/2022-09/Abhisek%20Tamang.jpg",
                createdAt = "2023-09-03T10:00:00Z",
                updatedAt = "2023-09-03T10:00:00Z",
                deleted = false
            )
        ),
        createdAt = "2023-09-03T11:00:00Z",
        updatedAt = "2023-09-03T11:00:00Z",
        deleted = false
    )
    val channelResponse = ChannelResponse(
        id = "channel1"
    )

    val priorityHigh = PriorityTaskResponse(
        id = "priority1",
        name = "High",
        description = "High priority",
        color = "#FF0000",
        createdAt = "2023-09-03T10:00:00Z",
        updatedAt = "2023-09-03T10:00:00Z",
        deleted = false
    )

    val priorityMedium = PriorityTaskResponse(
        id = "priority2",
        name = "Medium",
        description = "Medium priority",
        color = "#FFFF00",
        createdAt = "2023-09-03T11:00:00Z",
        updatedAt = "2023-09-03T11:00:00Z",
        deleted = false
    )

    val priorityLow = PriorityTaskResponse(
        id = "priority3",
        name = "Low",
        description = "Low priority",
        color = "#00FF00",
        createdAt = "2023-09-03T12:00:00Z",
        updatedAt = "2023-09-03T12:00:00Z",
        deleted = false
    )

    val taskList = listOf(
        TaskResponse(
            id = "USER_002-TASK-001",
            createdBy = userResponse2,
            channel = channelResponse,
            assign = userResponse1,
            createdAt = "2023-09-03T13:00:00Z",
            updatedAt = "2023-09-03T13:00:00Z",
            priority = priorityHigh,
            status = "done",
            attachments = null,
            subtasks = null,
            name = "Menulis nama fauzan 100x",
            description = "harap menggunakan huruf besar semua",
            taskStart = "2028-12-03T13:00:00Z",
            taskEnd = "2028-12-03T13:04:50Z",
            deleted = false,
            publish = true
        ),
        TaskResponse(
            id = "USER_001-TASK-002",
            createdBy = userResponse1,
            channel = channelResponse,
            assign = userResponse2,
            createdAt = "2023-09-03T14:00:00Z",
            updatedAt = "2023-09-03T14:00:00Z",
            priority = priorityMedium,
            status = "in_progress",
            attachments = null,
            subtasks = null,
            name = "Membaca buku terbaru",
            description = "Novel fiksi ilmiah",
            taskStart = "2028-12-03T14:00:00Z",
            taskEnd = "2028-12-03T15:00:00Z",
            deleted = false,
            publish = true
        ),
        TaskResponse(
            id = "USER_001-TASK-003",
            createdBy = userResponse1,
            channel = channelResponse,
            assign = userResponse1,
            createdAt = "2023-09-03T15:00:00Z",
            updatedAt = "2023-09-03T15:00:00Z",
            priority = priorityMedium,
            status = "overtime",
            attachments = null,
            subtasks = null,
            name = "Belajar musik",
            description = "Latihan gitar",
            taskStart = "2028-12-03T15:00:00Z",
            taskEnd = "2028-12-03T16:00:00Z",
            deleted = false,
            publish = true
        ),
        TaskResponse(
            id = "USER_002-TASK-004",
            createdBy = userResponse2,
            channel = channelResponse,
            assign = userResponse1,
            createdAt = "2023-09-03T16:00:00Z",
            updatedAt = "2023-09-03T16:00:00Z",
            priority = priorityLow,
            status = "in_progress",
            attachments = null,
            subtasks = null,
            name = "Menulis ulasan buku",
            description = "Buku non-fiksi",
            taskStart = "2028-12-03T16:00:00Z",
            taskEnd = "2028-12-03T17:00:00Z",
            deleted = false,
            publish = true
        ),
        TaskResponse(
            id = "USER_002-TASK-005",
            createdBy = userResponse2,
            channel = channelResponse,
            assign = userResponse1,
            createdAt = "2023-09-03T17:00:00Z",
            updatedAt = "2023-09-03T17:00:00Z",
            priority = priorityMedium,
            status = "done",
            attachments = null,
            subtasks = null,
            name = "Pelajari bahasa baru",
            description = "Bahasa Spanyol",
            taskStart = "2028-12-03T17:00:00Z",
            taskEnd = "2028-12-03T18:00:00Z",
            deleted = false,
            publish = true
        )
    )

    HomeScreen(
        DashboardState(
            fullName = "Olivia Rhye",
            favoriteItemTask = taskList,
            listAllTaskHome = mapOf(
                "Tugas hari ini" to taskList,
                "Tugas besok" to taskList,
                "Tugas minggu ini" to taskList,
            )
        )
    )
}