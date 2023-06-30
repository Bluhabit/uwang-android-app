/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.completeProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.BaseScreen
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import app.trian.mvi.ui.internal.rememberUIController
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetGenderPicker
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetSpinnerDatePicker
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.FormInput
import com.bluehabit.core.ui.components.input.FormInputWithIcon
import com.bluehabit.core.ui.routes.AuthenticationConstants
import com.bluehabit.core.ui.routes.Routes


@Navigation(
    route = Routes.CompleteProfile.routeName,
    group = AuthenticationConstants.parentRoute,
    viewModel = CompleteProfileViewModel::class
)
@Composable
fun CompleteProfileScreen(
    uiContract: UIContract<CompleteProfileState,CompleteProfileIntent,CompleteProfileAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(
    uiContract
) {

    BaseScreen(
        topAppBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                },
                title = {},
                elevation = 0.dp
            )
        },
        bottomSheet = {
            when (state.contentBottomSheet) {
                "DatePicker" -> {
                    BottomSheetSpinnerDatePicker(
                        title = stringResource(R.string.title_picker_date_of_birth_bottom_sheet),
                        textButtonConfirmation = stringResource(R.string.text_button_confirmation_date_of_birth_bottom_sheet),
                        onDismiss = {
                           // hideBottomSheet()
                        },
                        onConfirm = {
                            commit { copy(dateOfBirth = it) }
                            //hideBottomSheet()
                        }
                    )
                }

                else -> {
                    BottomSheetGenderPicker(
                        title = stringResource(R.string.title_picker_gender_bottom_sheet),
                        onDismiss = {
                            //hideBottomSheet()
                        },
                        onConfirm = {
                            commit { copy(gender = it) }
                            //hideBottomSheet()
                        }
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.text_title_complete_profile),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.text_subtitle_complete_profile),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    FormInput(
                        value = state.fullName,
                        label = stringResource(R.string.label_input_full_name),
                        placeholder = stringResource(R.string.placeholder_input_full_name),
                        onChange = {
                            commit {
                                copy(
                                    fullName = it
                                )
                            }
                        },
                        keyboardActions = KeyboardActions(
                            onNext = {
                                commit { copy(contentBottomSheet = "DatePicker") }
                              //  showBottomSheet()
                            }
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    FormInputWithIcon(
                        value = if (state.dateOfBirth == null) "" else state.dateOfBirth.toString(),
                        label = stringResource(R.string.label_input_date_of_birth_complete_profile),
                        placeholder = stringResource(R.string.placeholder_input_date_of_birth_complete_profile),
                        clickable = true,
                        onClick = {
                           // hideKeyboard()
                            commit { copy(contentBottomSheet = "DatePicker") }
                           // showBottomSheet()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.DateRange,
                                contentDescription = "",
                                tint = if (state.dateOfBirth == null) Color.LightGray else MaterialTheme.colors.primary
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    FormInputWithIcon(
                        value = if (state.gender == null) "" else stringResource(state.gender!!.label),
                        label = stringResource(R.string.label_input_gender_complete_profile),
                        placeholder = stringResource(R.string.placeholder_input_gender_complete_profile),
                        clickable = true,
                        onClick = {
                           // hideKeyboard()
                            commit { copy(contentBottomSheet = "GenderPicker") }
                           // showBottomSheet()
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowDown,
                                contentDescription = "",
                                tint = if (state.gender == null) Color.LightGray else MaterialTheme.colors.primary
                            )
                        }
                    )
                }
            }
            ButtonPrimary(
                modifier = Modifier.align(Alignment.BottomCenter),
                enabled = (state.fullName.isNotEmpty() && state.dateOfBirth != null && state.gender != null),
                text = stringResource(R.string.text_button_confirmation_complete_profile)
            ) {
                navigator.navigateAndReplace(Routes.CreateNewPassword.routeName)
            }
        }
    }


}

@Preview
@Composable
fun PreviewScreenCompleteProfile() {

    MaterialTheme {
        CompleteProfileScreen(
            uiContract = UIContract(
                controller = rememberUIController(),
                state = CompleteProfileState()
            )
        )
    }
}
