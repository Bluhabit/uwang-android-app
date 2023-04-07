/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.completeProfile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.BottomSheetDatePicker
import com.bluehabit.budgetku.android.components.BottomSheetGenderPicker
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.components.FormInputWithIcon

object CompleteProfile {
    const val routeName = "CompleteProfile"
}

fun NavGraphBuilder.routeCompleteProfile(
    state: ApplicationState,
) {
    composable(CompleteProfile.routeName) {
        ScreenCompleteProfile(appState = state)
    }
}

@Composable
internal fun ScreenCompleteProfile(
    appState: ApplicationState,
) = UIWrapper<CompleteProfileViewModel>(appState = appState) {
    val state by uiState.collectAsState()

    with(appState) {
        setupTopAppBar {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                },
                title = {},
                elevation = 0.dp
            )
        }
        setupBottomSheet {
            when (state.contentBottomSheet) {
                "DatePicker" -> {
                   BottomSheetDatePicker(
                       onDismiss = {
                           hideBottomSheet()
                       },
                       onConfirm = {
                           commit { copy(dateOfBirth = it) }
                           hideBottomSheet()
                       }
                   )

                }

                else -> {
                    BottomSheetGenderPicker(
                        onDismiss = {
                            hideBottomSheet()
                        },
                        onConfirm = {
                            commit { copy(gender = it) }
                            hideBottomSheet()
                        }
                    )
                }
            }
        }
    }
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
                text = "Beritahu Kami Tentang Dirimu",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Masukkan data diri kamu, termasuk nama, tanggal lahir dan Pastikan kamu memasukkan informasi yang benar dan akurat.",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormInput(
                value = state.fullName,
                label = "Nama Lengkap",
                placeholder = "Masukkan Nama Lengkapmu",
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
                        showBottomSheet()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormInputWithIcon(
                value = "",
                label = "Tanggal Lahir",
                placeholder = "Masukkan Tanggal Lahirmu",
                clickable = true,
                onClick = {
                    commit { copy(contentBottomSheet = "DatePicker") }
                    showBottomSheet()
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = ""
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            FormInputWithIcon(
                value = "",
                label = "Jenis Kelamin",
                placeholder = "Masukkin jenis kelaminmu",
                clickable = true,
                onClick = {
                    commit { copy(contentBottomSheet = "GenderPicker") }
                    showBottomSheet()
                },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = ""
                    )
                }
            )
        }
        ButtonPrimary(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Konfirmasi"
        ) {

        }
    }
}

@Preview
@Composable
fun PreviewScreenCompleteProfile() {
    BaseMainApp(
        topAppBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                },
                title = {},
                elevation = 0.dp
            )
        }
    ) {
        ScreenCompleteProfile(it)
    }
}