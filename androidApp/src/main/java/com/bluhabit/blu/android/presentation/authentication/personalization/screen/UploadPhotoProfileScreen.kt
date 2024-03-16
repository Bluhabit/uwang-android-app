/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.personalization.screen

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.common.checkGrantedPermissionFrom
import com.bluhabit.blu.android.presentation.authentication.personalization.PersonalizationAction
import com.bluhabit.blu.android.presentation.authentication.personalization.PersonalizationState
import com.bluhabit.core.ui.components.button.ButtonOutlinedPrimary
import com.bluhabit.core.ui.components.button.ButtonPrimary
import com.bluhabit.core.ui.components.dialog.DialogChoice
import com.bluhabit.core.ui.ext.getBitmap
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun UploadPhotoProfileScreen(
    modifier: Modifier = Modifier,
    state: PersonalizationState = PersonalizationState(),
    onAction: (PersonalizationAction) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    val choiceList = listOf(
        "Take Photo",
        "Choose existing photo"
    )
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null) {
            val bitmap = result.getBitmap(ctx.contentResolver)
            if (bitmap != null) {
                onAction(PersonalizationAction.OnShowDialogChoice(false))
                onAction(PersonalizationAction.OnProfileImageChange(bitmap))
            }
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            onAction(PersonalizationAction.OnShowDialogChoice(false))
            onAction(PersonalizationAction.OnProfileImageChange(bitmap))
        }
    }

    val requestPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            if (!it.containsValue(false)) {
                onAction(PersonalizationAction.OnShowDialogChoice(true))
            } else {
                Toast.makeText(ctx, "Permission declined", Toast.LENGTH_SHORT).show()
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(UwangColors.Base.White)
            .safeDrawingPadding(),
    ) {
        if (state.showDialogChoice) {
            DialogChoice(
                items = choiceList,
                onSelected = {
                    when (it) {
                        0 -> {
                            cameraLauncher.launch()
                        }

                        1 -> imageLauncher.launch("image/*")
                    }
                },
                onDismissRequest = {
                    onAction(PersonalizationAction.OnShowDialogChoice(false))
                }
            )
        }
        Box(
            modifier = modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "",
                modifier = modifier
                    .size(dimens.dp_24)
                    .align(Alignment.Center)
            )

        }
        Spacer(modifier = modifier.padding(bottom = dimens.dp_24))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.title_header_photo_profile),
                style = UwangTypography.BodyXL.SemiBold,
                color = UwangColors.Text.Main
            )
            Text(
                text = stringResource(id = R.string.description_header_photo_profile),
                style = UwangTypography.BodySmall.Regular,
                color = UwangColors.Text.Secondary
            )
        }
        Spacer(modifier = modifier.padding(bottom = dimens.dp_24))
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .width(dimens.from(188.dp))
                    .height(dimens.from(224.dp))
                    .background(UwangColors.Text.LayoutBackground, shape = RoundedCornerShape(12.dp))
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = UwangColors.Text.Border
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(dimens.dp_12),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = modifier
                            .size(dimens.from(144.dp))
                            .clip(CircleShape)
                            .background(UwangColors.Text.LayoutBackground)
                            .border(
                                border = BorderStroke(
                                    width = if (state.profileImage == null) 1.dp else 0.dp,
                                    color = UwangColors.Text.Border
                                ),
                                shape = CircleShape
                            )
                            .clickable {
                                val requiredPermissionList = arrayOf(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                )
                                val isAllPermissionGranted = requiredPermissionList
                                    .checkGrantedPermissionFrom(ctx)
                                if (isAllPermissionGranted) {
                                    onAction(PersonalizationAction.OnShowDialogChoice(true))
                                } else {
                                    requestPermissionContract.launch(
                                        requiredPermissionList
                                    )
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.profileImage == null) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(painter = painterResource(id = R.drawable.outlined_camera), contentDescription = "")
                                Text(
                                    text = stringResource(id = R.string.label_teks_button_upload),
                                    style = UwangTypography.LabelLarge.Regular,
                                    color = UwangColors.State.Primary.Main
                                )
                            }
                        } else {
                            Image(
                                bitmap = state.profileImage.asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
                    Text(
                        text = "@".plus(state.usernameValueState),
                        style = UwangTypography.BodyMedium.Regular,
                        color = UwangColors.Text.Secondary
                    )
                }
            }
        }
        Spacer(modifier = modifier.weight(1f))
        Spacer(
            modifier = modifier
                .padding(top = 24.dp) // Untuk jaga-jaga kalau layar di rotasi dan tidak bisa pakai weight 1f karena tidak ada ruang kosong)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(UwangColors.Base.White)
        ) {
            Divider(
                color = UwangColors.Text.Border
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.dp_16, vertical = dimens.dp_24)
            ) {
                ButtonOutlinedPrimary(
                    modifier = modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_pass),
                    onClick = { onAction(PersonalizationAction.NextSkip) }
                )
                ButtonPrimary(
                    modifier = modifier
                        .width(dimens.from(102.dp))
                        .height(dimens.from(36.dp)),
                    text = stringResource(id = R.string.label_button_next),
                    enabled = state.profileImage != null && state.uploadProfileSuccess,
                    onClick = {
                        onAction(PersonalizationAction.UploadPhotoProfileNextButton)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun UploadPhotoProfileScreenPreview() {
    UwangTheme {
        UploadPhotoProfileScreen()
    }
}