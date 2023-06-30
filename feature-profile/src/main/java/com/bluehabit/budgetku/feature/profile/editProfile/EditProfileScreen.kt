/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.profile.editProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.Navigation
import app.trian.mvi.ui.UIWrapper
import app.trian.mvi.ui.internal.UIContract
import app.trian.mvi.ui.internal.listener.BaseEventListener
import app.trian.mvi.ui.internal.listener.EventListener
import com.bluehabit.budgetku.data.R
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.BaseScreen
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetGenderPicker
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetSelectAvatar
import com.bluehabit.core.ui.components.bottomSheet.BottomSheetSpinnerDatePicker
import com.bluehabit.core.ui.components.button.ButtonPrimary
import com.bluehabit.core.ui.components.input.FormInput
import com.bluehabit.core.ui.components.input.FormInputWithIcon
import com.bluehabit.core.ui.routes.Routes

@Navigation(
    route = Routes.EditProfile.routeName,
    viewModel = EditProfileViewModel::class
)
@Composable
fun EditProfileScreen(
    uiContract: UIContract<EditProfileState, EditProfileIntent, EditProfileAction>,
    event: BaseEventListener = EventListener(),
) = UIWrapper(uiContract) {

    BaseScreen(
        topAppBar = {
            TopAppBarEditProfile(
                onBackPressed = {
                    navigator.navigateUp()
                }
            )
        },
        bottomSheet = {
            when (state.bottomSheetType) {
                "avatar" -> BottomSheetSelectAvatar(
                    avatars = state.avatars,
                    onSelected = {
                        //hideBottomSheet()
                    }
                )

                "dateOfBirth" -> BottomSheetSpinnerDatePicker(
                    title = "Ubah Tanggal lahir",
                    onDismiss = {
                        //hideBottomSheet()
                    },
                    onConfirm = {
                        commit {
                            copy(
                                dateOfBirth = it.toString()
                            )
                        }
                        //hideBottomSheet()
                    },
                    textButtonConfirmation = "Simpan"
                )

                else -> BottomSheetGenderPicker(
                    onDismiss = {
                        //hideBottomSheet()
                    },
                    onConfirm = {
                       // hideBottomSheet()
                    }
                )
            }
        }
    ) {
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
                        //showBottomSheet()
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
                        //showBottomSheet()
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
                        //showBottomSheet()
                        commit {
                            copy(
                                bottomSheetType = ""
                            )
                        }
                    }
                )
            }

            ButtonPrimary(text = "Simpan") {
                navigator.navigateUp()
            }

        }
    }


}

@Composable
fun TopAppBarEditProfile(
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = "Edit Profile")
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onBackPressed
                )
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun PreviewScreenEditProfile() {
    BaseMainApp() {
        EditProfileScreen(
            uiContract = UIContract(
                controller = it,
                state = EditProfileState()
            )
        )
    }
}