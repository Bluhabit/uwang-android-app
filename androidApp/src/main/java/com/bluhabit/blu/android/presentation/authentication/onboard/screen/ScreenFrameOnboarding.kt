/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    header: @Composable () -> Unit = {},
    indicator: @Composable () -> Unit = {},
    mid: @Composable () -> Unit = {},
    bottom: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = modifier.padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                indicator()
            }
        }
        header()
        mid()
        bottom()
    }
}