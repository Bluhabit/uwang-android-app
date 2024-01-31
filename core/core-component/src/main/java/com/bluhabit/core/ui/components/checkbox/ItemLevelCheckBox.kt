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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun ItemLevelCheckBox(
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit = {},
    title: String = String.Empty,
    description: String = String.Empty,
    checked: Boolean = false,
    onCheckedChange: () -> Unit = {},
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = UwangColors.Text.Border,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White)
            .clickable(onClick = onCheckedChange)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            image()
            Text(
                text = title,
                style = UwangTypography.BodyMedium.SemiBold,
                color = UwangColors.Base.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            if (checked) {
                Image(
                    painter = painterResource(id = R.drawable.ic_radio_checked),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_16)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_radio_unchecked),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.dp_16)
                )
            }
        }
        Text(
            text = description,
            style = UwangTypography.BodyXS.Regular,
            color = UwangColors.Text.Secondary
        )
    }
}

@Preview
@Composable
fun ItemLevelCheckBoxPreview() {
    var isChecked by remember {
        mutableStateOf(false)
    }
    UwangTheme {
        ItemLevelCheckBox(
            modifier = Modifier
                .padding(16.dp),
            image = {
                Image(
                    painter = painterResource(id = R.drawable.image_level_1),
                    contentDescription = ""
                )
            },
            title = "Pemula",
            description = "Seseorang yang mengetahui konsep dasar keuangan seperti pengeluaran, pendapatan, anggaran, dan tabungan.",
            checked = isChecked
        ) {
            isChecked = !isChecked
        }
    }
}