/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.input.picker

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.trian.mvi.ui.extensions.dashedBorder
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.GaweanTheme
import com.bluehabit.core.ui.theme.Gray200
import com.bluehabit.core.ui.theme.Gray400
import com.bluehabit.core.ui.theme.Primary200

@Composable
fun InputFilePicker(
    modifier: Modifier = Modifier,
    items: List<String> = listOf("HEHE","HOOHO"),
    onPickFile:()->Unit  = {}
) {
    Column {
        Text(
            text = "Lampiran",
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier
        )
        Text(
            text = "Batas upload 3 File PNG/JPG ukuran file max. 5MB",
            style = MaterialTheme.typography.body1.copy(
                fontSize = 10.sp
            ),
            color = Gray400
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilePicker(
                onClick=onPickFile
            )
            items.forEach {
                FilePreview()
            }
        }
    }
}

@Composable
fun FilePicker(
    modifier: Modifier = Modifier,
    onClick:()->Unit={}
) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(
                RoundedCornerShape(
                    8.dp
                )
            )
            .clickable(onClick = onClick)
            .dashedBorder(
                width = 1.dp,
                color = Primary200,
                shape = RoundedCornerShape(8.dp),
                on = 8.dp,
                off = 4.dp
            )
            .padding(
                12.dp
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "",
            modifier = modifier.align(Alignment.Center),
            tint = Primary200
        )
    }
}

@Composable
fun FilePreview(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.emptylistpage
            ),
            contentDescription = "",
            modifier = modifier.size(72.dp).background(Gray200),
            contentScale = ContentScale.FillBounds
        )
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "",
            modifier = modifier.align(Alignment.TopEnd),
            tint = Primary200
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewInputFilePicker() {
    GaweanTheme {
        InputFilePicker()
    }
}