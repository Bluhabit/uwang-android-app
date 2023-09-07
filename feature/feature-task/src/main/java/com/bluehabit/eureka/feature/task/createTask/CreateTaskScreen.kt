/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.task.createTask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.contract.UIContract
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.input.BaseInputTextPrimary
import com.bluehabit.core.ui.components.input.InputSubTask
import com.bluehabit.core.ui.components.input.InputTextArea
import com.bluehabit.core.ui.components.input.picker.InputDatePicker
import com.bluehabit.core.ui.components.sheet.DatePickerBottomSheet
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.theme.Error500
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray400
import com.bluehabit.core.ui.theme.Gray700
import com.bluehabit.core.ui.theme.Gray900
import com.bluehabit.eureka.data.dateTimeConstant.DATE_PATTERN
import com.bluehabit.eureka.data.ext.localDateToOffset
import com.bluehabit.eureka.data.ext.offsetToDate
import com.bluehabit.eureka.data.task.datasource.remote.request.SubtaskRequest
import com.bluehabit.eureka.data.task.datasource.remote.response.SubTaskResponse

@Navigation(
    route = Routes.CreateTask.routeName,
    viewModel = CreateViewModel::class
)
@Composable
fun CreateTaskScreen(
    uiContract: UIContract<CreateTaskState, CreateTaskAction>,
    modifier: Modifier = Modifier
) = UIWrapper(uiContract = uiContract) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,
        )
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetContent = {
            if (state.openStartDate) {
                DatePickerBottomSheet(
                    title = "Mulai",
                    onDone = {
                        focusManager.clearFocus()
                        commit {
                            copy(openStartDate = false)
                        }
                        launch {
                            modalSheetState.hide()
                        }
                    }
                ) { localDate ->
                    focusManager.clearFocus()
                    commit {
                        copy(
                            startDate = localDate.localDateToOffset(),
                        )
                    }
                }

            } else if (state.openEndDate) {
                DatePickerBottomSheet(
                    title = "Selesai",
                    onDone = {
                        focusManager.clearFocus()
                        commit {
                            copy(openEndDate = false)
                        }
                        launch {
                            modalSheetState.hide()
                        }
                    },
                ) {localDate ->
                    focusManager.clearFocus()
                    commit {
                        copy(
                            endDate = localDate.localDateToOffset(),
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    elevation = 0.dp,
                    backgroundColor = Color.White,
                    title = {

                        Text(
                            text = stringResource(id = R.string.text_top_bar_create_task),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.W600,
                            color = Gray900,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = stringResource(id = R.string.description_icon_arrow)
                            )
                        }
                    }
                )
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = it.calculateTopPadding(),
                    bottom = 14.dp,
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Nama Tugas",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            fontWeight = FontWeight.W500,
                            color = Gray700,
                        )
                        BaseInputTextPrimary(
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.text_placeholder_input_title_create_task),
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.W400,
                                    fontSize = 14.sp
                                )
                            },
                            error = state.titleTaskError,
                            value = state.titleTask,
                            onChange = { newValue ->
                                if (newValue.length <= 100) {
                                    commit {
                                        copy(
                                            titleTask = newValue,
                                            titleTaskError = false,
                                            titleTaskErrorMessage = "Nama tugas max. 100 karakter"
                                        )
                                    }
                                } else {
                                    commit { copy(titleTaskError = true) }
                                }
                            }
                        )
                        if (state.titleTaskError) {
                            Text(
                                text = state.titleTaskErrorMessage,
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.W400,
                                color = Error500,
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                }
                stickyHeader {
                    Text(
                        text = "Subtugas",
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Medium,
                        ),
                        fontWeight = FontWeight.W500,
                        color = Gray700,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }
                itemsIndexed(state.listSubTask) { index, subTask ->
                    InputSubTask(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 20.dp),
                        value = subTask.subTaskName,
                        checked = subTask.done,
                        onValueChange = { newValue ->
                            if (newValue.isEmpty()) {
                                // Please make sure swipe checkedState to false if TextField is empty
                            }
                        },
                        onCheckedChange = {

                        },
                        onDeleteClicked = {

                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                dispatch(CreateTaskAction.AddNewSubTask)
                            }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = stringResource(id = R.string.text_description_ic_add_create_task)
                        )
                        Text(
                            text = "Tambah subtugas",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            fontWeight = FontWeight.W500,
                            color = Gray400,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Deskripsi",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            fontWeight = FontWeight.W500,
                            color = Gray700,
                        )
                        InputTextArea(
                            placeholder = stringResource(id = R.string.text_placeholder_input_description_create_task),
                            error = state.descriptionTaskError,
                            value = state.descriptionTask,
                            onChange = { newValue ->
                                if (newValue.length <= 100) {
                                    commit {
                                        copy(
                                            descriptionTask = newValue,
                                            descriptionTaskError = false,
                                            descriptionTaskErrorMessage = "Deskripsi tugas max. 100 karakter"
                                        )
                                    }
                                } else {
                                    commit { copy(descriptionTaskError = true) }
                                }
                            }
                        )
                        if (state.descriptionTaskError) {
                            Text(
                                text = state.descriptionTaskErrorMessage,
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.W400,
                                color = Error500,
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 12.dp))
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "Durasi",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                            ),
                            fontWeight = FontWeight.W500,
                            color = Gray700,
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            InputDatePicker(
                                modifier = Modifier
                                    .weight(1f),
                                placeholder = "Pilih mulai",
                                value = state.startDate.offsetToDate(DATE_PATTERN).orEmpty(),
                                error = state.startDateError,
                                onClick = {
                                    keyboardController?.hide()
                                    commit {
                                        copy(openStartDate = true)
                                    }
                                    launch {
                                        modalSheetState.show()
                                    }
                                }
                            )
                            InputDatePicker(
                                modifier = Modifier
                                    .weight(1f),
                                placeholder = "Pilih selesai",
                                value = state.endDate.offsetToDate(DATE_PATTERN).orEmpty(),
                                error = state.endDateError,
                                enabled = state.startDate.isNotEmpty(),
                                onClick = {
                                    keyboardController?.hide()
                                    commit {
                                        copy(openEndDate = true)
                                    }
                                    launch {
                                        modalSheetState.show()
                                    }
                                }
                            )
                        }
                    }
                    if (state.startDateError || state.endDateError) {
                        Text(
                            text = state.dateErrorMessage,
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.W400,
                            color = Error500,
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewDashboardScreen() {
    GaweanTheme() {
        val subTask = listOf(
            SubTaskResponse(
                id = "SUBTASK_001",
                name = "Menonton anime",
                createdAt = "2023-09-05T12:41:43.440-03:00",
                updatedAt = "2023-09-05T12:41:43.440-03:00",
                done = false,
                deleted = false
            ),
            SubTaskResponse(
                id = "SUBTASK_002",
                name = "Menonton drakor",
                createdAt = "2023-09-05T12:41:43.440-03:00",
                updatedAt = "2023-09-05T12:41:43.440-03:00",
                done = true,
                deleted = false
            ),
            SubTaskResponse(
                id = "SUBTASK_002",
                name = "SitUp 10x",
                createdAt = "2023-09-05T12:41:43.440-03:00",
                updatedAt = "2023-09-05T12:41:43.440-03:00",
                done = false,
                deleted = false
            )
        )
        var state by remember {
            mutableStateOf(CreateTaskState())
        }
        CreateTaskScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = state,
                mutation = {
                    state = it
                },
                dispatcher = {
                    when(it){
                        CreateTaskAction.AddNewSubTask -> {
                            val subs = state.listSubTask.toMutableList()
                            subs.add(SubtaskRequest("",false))
                            state = state.copy(listSubTask = subs)
                        }
                        CreateTaskAction.CreateTemporaryTask -> Unit
                        CreateTaskAction.PublishTask -> Unit
                        CreateTaskAction.UploadAttachment -> Unit
                    }
                }
            )
        )
    }

}