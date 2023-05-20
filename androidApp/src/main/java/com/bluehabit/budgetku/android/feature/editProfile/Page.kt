/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetGenderPicker
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetSelectAvatar
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetSpinnerDatePicker
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.components.input.FormInput
import com.bluehabit.budgetku.android.components.input.FormInputWithIcon
import com.bluehabit.budgetku.data.R

object EditProfile {
    const val routeName = "EditProfile"
}

fun NavGraphBuilder.routeEditProfile(
    state: ApplicationState,
) {
    composable(EditProfile.routeName) {
        ScreenEditProfile(appState = state)
    }
}

@Composable
internal fun ScreenEditProfile(
    appState: ApplicationState,
) = UIWrapper<EditProfileViewModel>(appState = appState) {

    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()
    with(appState) {
        setupTopAppBar {
            TopAppBarEditProfile()
        }
        setupBottomSheet {
            when (state.bottomSheetType) {
                "avatar" -> BottomSheetSelectAvatar(
                    avatars = dataState.avatars,
                    onSelected = {
                        hideBottomSheet()
                    }
                )

                "dateOfBirth" -> BottomSheetSpinnerDatePicker(
                    onDismiss = {
                        hideBottomSheet()
                    },
                    onConfirm = {
                        commit {
                            copy(
                                dateOfBirth = it.toString()
                            )
                        }
                        hideBottomSheet()
                    }
                )

                else -> BottomSheetGenderPicker(
                    onDismiss = {
                        hideBottomSheet()
                    },
                    onConfirm = {

                    }
                )
            }

        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy_avatar),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            TextButton(
                onClick = {
                    showBottomSheet()
                    commit {
                        copy(
                            bottomSheetType = "avatar"
                        )
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = "Ubah Avatar"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )
            FormInput(
                label = "Nama",
                value = state.fullName,
            )
            FormInputWithIcon(
                label = "Tanggal Lahir",
                value = state.dateOfBirth,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = ""
                    )
                },
                clickable = true,
                onClick = {
                    showBottomSheet()
                    commit {
                        copy(
                            bottomSheetType = "dateOfBirth"
                        )
                    }
                }
            )
            FormInputWithIcon(
                label = "Janis Kelamin",
                value = state.gender,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = ""
                    )
                },
                clickable = true,
                onClick = {
                    showBottomSheet()
                    commit {
                        copy(
                            bottomSheetType = ""
                        )
                    }
                }
            )
        }

        ButtonPrimary(text = "Simpan") {
            navigateUp()
        }

    }
}

@Composable
fun TopAppBarEditProfile() {
    TopAppBar(
        title = {
            Text(text = "Edit Profile")
        },
        navigationIcon = {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun PreviewScreenEditProfile() {
    BaseMainApp(
        topAppBar = {
            TopAppBarEditProfile()
        }
    ) {
        ScreenEditProfile(it)
    }
}