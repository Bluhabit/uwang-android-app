/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileAction
import com.bluhabit.blu.android.presentation.authentication.completeProfile.CompleteProfileState
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.sheet.DatePickerBottomSheet
import com.bluhabit.core.ui.components.textfield.TextFieldPrimary
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.launch

@Composable
fun InputDobScreen(
    state: CompleteProfileState = CompleteProfileState(),
    onBackPressed: () -> Unit = {},
    onAction: (CompleteProfileAction) -> Unit = {},
) {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetContent = {
            DatePickerBottomSheet(
                title = "Title Here",
                minDate = LocalDate.MIN,
                onClose = {
                    scope.launch {
                        modalSheetState.hide()
                    }
                },
                onDone = {
                    scope.launch {
                        modalSheetState.hide()
                    }
                },
                onChange = { localDate ->
                    onAction(CompleteProfileAction.OnDateOfBirthChange(localDate))
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .verticalScroll(rememberScrollState()),
        ) {
            IconButton(onClick = {
                onBackPressed()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "arrow back"
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.input_odb_screen_title),
                    style = CustomTypography.Body.XL.W600,
                    color = CustomColor.Neutral.Grey13
                )
                Text(
                    text = stringResource(id = R.string.input_odb_screen_instruction),
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey9
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.input_odb_screen_dob_text_field_title),
                    style = CustomTypography.Body.Small.W400,
                    color = CustomColor.Neutral.Grey9
                )
                TextFieldPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = false,
                    colors = if (state.dateOfBirthState == null) {
                        TextFieldDefaults.outlinedTextFieldColors(
                            disabledBorderColor = CustomColor.Neutral.Grey6,
                            disabledTextColor = CustomColor.Neutral.Grey7
                        )
                    } else {
                        TextFieldDefaults.outlinedTextFieldColors(
                            disabledBorderColor = CustomColor.Neutral.Grey13,
                            disabledTextColor = CustomColor.Neutral.Grey13
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = R.string.input_odb_screen_dob_text_field_text)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                modalSheetState.show()
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_down),
                                contentDescription = "arrow back",
                                tint = CustomColor.Neutral.Grey7
                            )
                        }
                    },
                    value = state.dateOfBirthState?.format(DateTimeFormatter.ofPattern("dd-mm-yyyy")).orEmpty(),
                    onValueChange = {

                    },
                    error = state.dateOfBirthError
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                text = stringResource(id = R.string.input_odb_screen_dob_next_button_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 42.dp),
                enabled = true
            ) {
               onAction(CompleteProfileAction.NextStep)
            }
        }
    }
}

@Preview
@Composable
fun InputDobScreenPreview() {
    UwangTheme {
        InputDobScreen(
            onBackPressed = {}
        )
    }
}