/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.UwangColors
import com.bluhabit.core.ui.theme.UwangDimens
import com.bluhabit.core.ui.theme.UwangTheme
import com.bluhabit.core.ui.theme.UwangTypography

@Composable
fun CompleteProfileCard(
    modifier: Modifier = Modifier,
    completedStep: Int = 0,
    sizeStep: Int = 0
) {
    val ctx = LocalContext.current
    val dimens = UwangDimens.from(ctx)
    BaseProfileCard(
        modifier = modifier,
        height = dimens.from(86.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimens.from(4.dp)),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimens.dp_12),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.from(4.dp)),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_crown),
                    contentDescription = "",
                    modifier = Modifier
                        .size(dimens.from(40.dp))
                )
                Column {
                    Text(
                        text = "Lengkapi profil kamu",
                        style = UwangTypography.BodySmall.Medium,
                        color = UwangColors.Text.Main
                    )
                    Text(
                        text = "Tambahkan foto profil",
                        style = UwangTypography.BodyXS.Regular,
                        color = UwangColors.Text.Secondary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "+20 Points",
                    style = UwangTypography.BodyXS.Regular,
                    color = UwangColors.Text.Main
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$completedStep/$sizeStep",
                    style = UwangTypography.BodyXS.Regular,
                    color = UwangColors.Text.Secondary
                )
                LinearProgressIndicator(
                    progress = 0f,
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(1f)
                        .height(dimens.dp_8)
                        .drawWithContent {
                            drawContent()
                            drawRoundRect(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF3167E8),
                                        Color(0xFF004CFF),
                                        Color(0xFF1041B7),
                                    )
                                ),
                                blendMode = BlendMode.SrcAtop,
                                size = Size(width = size.width * (completedStep.toFloat() / sizeStep.toFloat()), height = size.height),
                                cornerRadius = CornerRadius(100f, 100f)
                            )
                        },
                    backgroundColor = UwangColors.State.Primary.Surface,
                )
            }
        }

    }
}

@Preview
@Composable
fun CompleteProfileCardPreview() {
    UwangTheme {
        CompleteProfileCard(
            modifier = Modifier
                .padding(top = 56.dp, start = 16.dp, end = 16.dp)
                .safeDrawingPadding(),
            completedStep = 1,
            sizeStep = 4
        )
    }
}