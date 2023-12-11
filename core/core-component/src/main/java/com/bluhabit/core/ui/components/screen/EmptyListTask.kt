/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.Gray500
import com.bluhabit.core.ui.theme.Gray600
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun EmptyListPage(
    modifier: Modifier = Modifier,
    label: String = String.Empty,
    title: String = String.Empty,
    message: String = String.Empty,
    icon: (@Composable () -> Unit)? = null,
) {
    Text(
        modifier = modifier.padding(
            horizontal = 18.dp
        ),
        text = label,
        style = MaterialTheme.typography.h6,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
        color = Gray600
    )
    Spacer(modifier = modifier.height(16.dp))
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon?.invoke()
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier
                .width(295.dp)
                .height(35.dp),
            text = title,
            style = MaterialTheme.typography.h6,
            lineHeight = 24.sp,
            fontWeight = FontWeight.W600,
            textAlign = TextAlign.Center,
            color = Gray600
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier
                .width(295.dp)
                .height(40.dp),
            text = message,
            style = MaterialTheme.typography.body2,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            color = Gray500,
        )
    }
}

@Composable
@Preview(widthDp = 500, heightDp = 750)
fun PreviewEmptyListTask() {
    UwangTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            EmptyListPage(
                label = stringResource(id = R.string.text_label_empty_list_task),
                icon = {
                    Image(
                        painterResource(id = R.drawable.empty_list_task),
                        contentDescription = "",
                        modifier = Modifier
                            .width(272.dp)
                            .height(197.81818.dp),
                    )
                },
                title = stringResource(id = R.string.text_title_empty_list),
                message = stringResource(id = R.string.text_placeholder_empty_list),)
        }
    }
}