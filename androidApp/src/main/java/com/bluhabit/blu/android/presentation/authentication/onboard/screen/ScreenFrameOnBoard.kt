/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.onboard.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun ScreenFrameOnBoard(
    modifier: Modifier = Modifier,
    headerTextColor: Color = UwangColors.Text.Secondary,
    indicatorColor: Color = UwangColors.State.Primary.Main,
    content: @Composable () -> Unit = {},
    skipOnboard: () -> Unit = {},
    currentPage: Int = 0,
    nextScreen: () ->  Unit = {},
    prevScreen: () ->  Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    Column(modifier = modifier.safeDrawingPadding()) {
        Row(
            modifier = Modifier.padding(dimens.dp_16),
            horizontalArrangement = Arrangement.spacedBy(dimens.from(4.dp))
        ) {
            LinearProgressIndicator(
                progress = 1f,
                modifier = Modifier
                    .weight(1f)
                    .height(dimens.from(4.dp)),
                color = indicatorColor,
                strokeCap = StrokeCap.Round
            )
            LinearProgressIndicator(
                progress = if (currentPage >= 1) 1f else 0f,
                modifier = Modifier
                    .weight(1f)
                    .height(dimens.from(4.dp)),
                color = indicatorColor,
                strokeCap = StrokeCap.Round,
            )
            LinearProgressIndicator(
                progress = if (currentPage >= 2) 1f else 0f,
                modifier = Modifier
                    .weight(1f)
                    .height(dimens.from(4.dp)),
                color = indicatorColor,
                strokeCap = StrokeCap.Round
            )
            LinearProgressIndicator(
                progress = if (currentPage >= 3) 1f else 0f,
                modifier = Modifier
                    .weight(1f)
                    .height(dimens.from(4.dp)),
                color = indicatorColor,
                strokeCap = StrokeCap.Round
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimens.dp_16
                ),
            horizontalArrangement = Arrangement.spacedBy(dimens.from(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "blu_logo",
                modifier = Modifier
                    .width(dimens.dp_24)
                    .height(dimens.dp_24)
            )
            Text(
                text = stringResource(id = R.string.label_header_logo),
                style = UwangTypography.BodyMedium.Regular,
                color = headerTextColor,
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
                    .padding(dimens.from(4.dp))
            ) {
                Icon(
                    modifier = Modifier
                        .clickable{
                            skipOnboard()
                        },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "ic_close",
                    tint = headerTextColor
                )

            }

        }
        Box {
            content()
            Row(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .background(Color.Transparent)
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        prevScreen()
                    }
                ) {

                }
                Box(modifier = Modifier
                    .background(Color.Transparent)
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        nextScreen()
                    }
                ) {

                }
            }
        }

    }
}