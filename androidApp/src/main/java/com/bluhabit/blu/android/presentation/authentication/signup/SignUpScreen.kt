/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.signup

import androidx.activity.compose.BackHandler
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.Routes
import com.bluhabit.blu.android.presentation.authentication.signup.screen.CompleteProfileSignUpScreen
import com.bluhabit.blu.android.presentation.authentication.signup.screen.InputSetNewPasswordSignUpScreen
import com.bluhabit.blu.android.presentation.authentication.signup.screen.InputSignUpScreen
import com.bluhabit.blu.android.presentation.authentication.signup.screen.OtpSignUpScreen
import com.bluhabit.core.ui.components.dialog.DialogLoading
import com.bluhabit.core.ui.components.sheet.DatePickerBottomSheet
import com.bluhabit.core.ui.components.sheet.GenderPickerBottomSheet
import com.bluhabit.core.ui.theme.UwangTheme
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navHostController: NavHostController = rememberNavController(),
    stateFlow: Flow<SignUpState> = flowOf(),
    effectFlow: Flow<SignUpEffect> = flowOf(),
    onAction: (SignUpAction) -> Unit = {},
) {
    val state by stateFlow.collectAsStateWithLifecycle(initialValue = SignUpState())
    val effect by effectFlow.collectAsState(initial = SignUpEffect.None)
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            if (state.bottomSheetType == BottomSheetSignUpType.DATE_OF_BIRTH && it == ModalBottomSheetValue.Hidden) {
                onAction(SignUpAction.ValidateDateOfBirth)
            }
            it != ModalBottomSheetValue.HalfExpanded
        },
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = effect, block = {
        when (effect) {
            SignUpEffect.None -> Unit
            SignUpEffect.NavigateToPersonalize -> {
                navHostController.navigate(Routes.Personalize) {
                    launchSingleTop = true
                    popUpTo(Routes.SignUp){
                        inclusive = true
                    }
                }
            }
        }
    })

    fun goBack() {
        if (state.currentScreen == 1) {
            onAction(SignUpAction.OnScreenChange(0))
        } else {
            navHostController.navigateUp()
        }
    }

    BackHandler {
        goBack()
    }
    DialogLoading(show = state.showLoading)
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetGesturesEnabled = false,
        sheetContent = {
            when (state.bottomSheetType) {
                BottomSheetSignUpType.GENDER -> {
                    GenderPickerBottomSheet(
                        title = stringResource(id = R.string.sign_up_bottom_sheet_gender_title),
                        value = state.genderState,
                        onDone = {
                            scope.launch {
                                bottomSheetState.hide()
                            }
                        },
                        onChange = {
                            onAction(SignUpAction.OnGenderChange(it))
                        },
                        onClose = {
                            scope.launch {
                                bottomSheetState.hide()
                            }
                        }
                    )
                }

                BottomSheetSignUpType.DATE_OF_BIRTH -> {
                    DatePickerBottomSheet(
                        title = stringResource(id = R.string.sign_up_bottom_sheet_date_of_birth_title),
                        value = state.dateOfBirthState,
                        minDate = LocalDate.MIN,
                        maxDate = LocalDate.now(),
                        onDone = {
                            scope.launch {
                                bottomSheetState.hide()
                                onAction(SignUpAction.ValidateDateOfBirth)
                            }
                        },
                        onClose = {
                            scope.launch {
                                bottomSheetState.hide()
                                onAction(SignUpAction.ValidateDateOfBirth)
                            }
                        },
                        onChange = {
                            onAction(SignUpAction.OnDateOfBirthChange(it))
                        }
                    )
                }
            }
        }
    ) {

        when (state.currentScreen) {
            0 -> InputSignUpScreen(
                state = state,
                onBackPressed = {
                    goBack()
                },
                action = onAction
            )

            1 -> OtpSignUpScreen(
                state = state,
                onBackPressed = {
                    goBack()
                },
                onAction = onAction
            )

            2 -> CompleteProfileSignUpScreen(
                state = state,
                onBackPressed = {
                    goBack()
                },
                action = onAction,
                onSelectDateOfBirth = {
                    scope.launch {
                        onAction(SignUpAction.OnShowBottomSheet(true, BottomSheetSignUpType.DATE_OF_BIRTH))
                        bottomSheetState.show()
                        focusManager.clearFocus(true)
                    }

                },
                onSelectGender = {
                    scope.launch {
                        onAction(SignUpAction.OnShowBottomSheet(true, BottomSheetSignUpType.GENDER))
                        bottomSheetState.show()
                        focusManager.clearFocus(true)
                    }
                }
            )

            3 -> InputSetNewPasswordSignUpScreen(
                state = state,
                onBackPressed = {
                    goBack()
                },
                action = onAction
            )
        }
    }
}

@Preview
@Composable
fun TermAndConditionScreenPreview() {
    UwangTheme {
        SignUpScreen()
    }
}
