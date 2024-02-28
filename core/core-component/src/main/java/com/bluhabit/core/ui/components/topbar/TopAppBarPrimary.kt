/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun TopAppBarPrimary(
    title: String = String.Empty,
    onBackPressed: () -> Unit = {},
    action: (@Composable () -> Unit)? = null
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    TopAppBar(
        modifier = Modifier
            .height(dimens.from(44.dp)),
        backgroundColor = Color.Transparent,
        contentPadding = PaddingValues(horizontal = dimens.dp_16, vertical = dimens.dp_8),
        elevation = dimens.from(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "",
            tint = UwangColors.Text.Main,
            modifier = Modifier
                .size(dimens.dp_24)
                .clickable(onClick = onBackPressed)
        )
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Text(
            text = title,
            style = UwangTypography.BodyLarge.SemiBold,
            color = UwangColors.Text.Main,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (action != null) {
            action()
        }
    }
}

@Preview
@Composable
fun TopAppBarPrimaryPreview() {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    UwangTheme {
        Box(
            modifier = Modifier
                .safeDrawingPadding()
                .background(UwangColors.Base.White)
                .fillMaxSize()
                .padding(top = dimens.from(48.dp))
        ) {
            TopAppBarPrimary(
                title = "Detail Screen"
            )
        }
    }
}