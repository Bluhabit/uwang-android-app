/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography
import kotlinx.coroutines.launch

@Composable
fun PickProfileImageBottomSheet(
    onTakePicture: () -> Unit = {},
    onPickFromGallery: () -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimens.dp_24, horizontal = dimens.dp_16),
        verticalArrangement = Arrangement.spacedBy(dimens.dp_16)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Divider(
                modifier = Modifier
                    .width(dimens.from(54.dp))
                    .height(dimens.from(4.dp))
                    .clip(RoundedCornerShape(dimens.dp_16)),
                color = UwangColors.Text.Secondary
            )
        }
        Text(
            text = stringResource(id = R.string.image_profile),
            style = UwangTypography.BodyLarge.SemiBold,
            color = UwangColors.Text.Main
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimens.dp_24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemBottomSheet(
                icon = R.drawable.ic_camera,
                text = stringResource(R.string.camera),
                onItemClick = { onTakePicture.invoke() }
            )
            ItemBottomSheet(
                icon = R.drawable.ic_gallery,
                text = stringResource(R.string.gallery),
                onItemClick = { onPickFromGallery.invoke() }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemBottomSheet(
    icon: Int,
    text: String,
    onItemClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(
        modifier = Modifier
            .clickable(onClick = onItemClick),
        verticalArrangement = Arrangement.spacedBy(dimens.from(4.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(dimens.from(64.dp))
                .border(
                    width = dimens.from(2.dp),
                    shape = CircleShape,
                    color = UwangColors.State.Primary.Border
                )
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = icon
                ),
                contentDescription = "$text + icon",
                tint = UwangColors.State.Primary.Main,
                modifier = Modifier
                    .size(dimens.dp_32)
            )
        }
        Text(
            text = text,
            style = UwangTypography.BodyMedium.Medium,
            color = UwangColors.Text.Main
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview(widthDp = 500, heightDp = 750, backgroundColor = 0xffffff)
fun PickProfileImageBottomSheetPreview() {
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,

        )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        sheetContent = {
            PickProfileImageBottomSheet()
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0x1A0D0D0D)),
        ) {
            Button(onClick = { scope.launch { modalSheetState.show() } }) {
                Text("Click to show sheet")
            }
        }

    }
}