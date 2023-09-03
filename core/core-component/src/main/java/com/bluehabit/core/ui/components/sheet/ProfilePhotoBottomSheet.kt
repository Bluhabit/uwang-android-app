/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Gray900
import kotlinx.coroutines.launch

@Composable
fun EditPhotoBottomSheet(
    onUploadImage: () -> Unit = {},
    onTakePicture: () -> Unit = {},
    onDeleteImage: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp)
    ) {
        ItemBottomSheet(
            icon = R.drawable.ic_upload,
            text = stringResource(R.string.text_upload_image_from_phone),
            onItemClick = { onUploadImage.invoke() }
        )
        ItemBottomSheet(
            icon = R.drawable.camera,
            text = stringResource(R.string.text_take_picture),
            onItemClick = { onTakePicture.invoke() }

        )
        ItemBottomSheet(
            icon = R.drawable.ic_trash,
            text = stringResource(R.string.text_delete_image),
            onItemClick = { onDeleteImage.invoke() }
        )
    }

}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemBottomSheet(
    icon: Int,
    text: String,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clickable(onClick = onItemClick),
        horizontalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(
                id = icon
            ),
            contentDescription = "$text + icon"
        )
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            color = Gray900
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview(widthDp = 500, heightDp = 750, backgroundColor = 0xffffff)
fun BottomSheetEditPhotoPreview() {
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
           EditPhotoBottomSheet()
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