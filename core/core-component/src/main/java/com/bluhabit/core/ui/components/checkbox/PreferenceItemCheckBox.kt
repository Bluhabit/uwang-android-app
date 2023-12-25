/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.UwangTheme

@Composable
fun PreferenceItemCheckBox(
    modifier: Modifier = Modifier,
    title: String = String.Empty,
    image: @Composable () -> Unit = {},
    checked: Boolean = false,
    onCheckedChange: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(154.dp)
            .height(121.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = CustomColor.Neutral.Grey6,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .clickable(onClick = onCheckedChange)
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .widthIn(max = 110.dp)
                    .heightIn(max = 70.dp)
            ) {
                image()
            }
            Spacer(modifier = Modifier.weight(1f))
            CheckedCircleCheckBox(
                checked = checked,
                enabled = false,
            )
        }
        Text(
            text = title,
            style = CustomTypography.Label.Small.W600,
            color = Color.Black
        )
    }

}

@Preview
@Composable
fun PreferenceItemCheckBoxPreview() {
    var checkedState by remember {
        mutableStateOf(false)
    }
    UwangTheme {
        PreferenceItemCheckBox(
            title = "Penganggaran",
            image = {
                Image(
                    painter = painterResource(id = R.drawable.preference_image_1),
                    contentDescription = "",
                )
            },
            checked = checkedState,
            onCheckedChange = {
                checkedState = !checkedState
            }
        )
    }
}