/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.Gray900
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangTypography

//@Composable
//fun ScreenFrameOnboarding(
//    modifier: Modifier = Modifier,
//    top: @Composable ColumnScope.() -> Unit = {},
//    middle: @Composable () -> Unit = {},
//    bottom: @Composable ColumnScope.() -> Unit = {}
//) {
//    Column(
//        modifier = modifier.fillMaxSize()
//    ) {
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(
//                    horizontal = 16.dp,
//                    vertical = 20.dp
//                ),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.app_logo),
//                contentDescription = "blu_logo",
//                modifier = modifier
//                    .width(24.dp)
//                    .height(24.dp)
//            )
//            Text(
//                text = stringResource(id = R.string.label_header_logo),
//                style = UwangTypography.BodyMedium.Regular,
//                color = UwangColors.Text.Secondary,
//            )
//            Spacer(modifier = modifier.weight(1f))
//            Icon(
//                painter = painterResource(id = R.drawable.ic_close),
//                contentDescription = "ic_close"
//            )
//        }
//        Column(
//            modifier = modifier
//                .fillMaxHeight(fraction = 0.6f)
//                .padding(
//                    start = 18.dp,
//                    end = 18.dp,
//                    bottom = 18.dp,
//                    top = 18.dp
//                ),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            top(this)
//        }
//        Column(
//            modifier = modifier.fillMaxHeight(fraction = 0.1f),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Box(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
//            ) {
//                middle()
//            }
//        }
//        Column(
//            modifier = modifier
//                .wrapContentHeight()
//                .padding(start = 18.dp, end = 18.dp, top = 18.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            bottom(this)
//        }
//    }
//}
@Composable
fun ScreenFrameOnboarding(
    modifier: Modifier = Modifier,
    text: Int,
    middle: @Composable () -> Unit = {},
    image: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "blu_logo",
                modifier = modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = stringResource(id = R.string.label_header_logo),
                style = UwangTypography.BodyMedium.Regular,
                color = UwangColors.Text.Secondary,
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "ic_close"
            )
        }
        Text(
            text = stringResource(id = text),
            style = UwangTypography.DisplayXS.SemiBold,
            color = Gray900,
        )
    }
}