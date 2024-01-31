/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.CustomColor
import com.bluhabit.core.ui.theme.CustomTypography
import com.bluhabit.core.ui.theme.Gray700
import com.bluhabit.core.ui.theme.UwangTheme

/**
 * Button Social
 * @param modifier
 * @param backgroundColor
 *  color of button
 * @param textColor
 *  color text button
 * @param icon
 *  icon button
 * @sample PreviewButtonSocial
 * */

@Composable
fun ButtonSocial(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    textColor: Color = Gray700,
    enabled: Boolean = true,
    backgroundColor: Color = Color.White,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .height(40.dp)
            .defaultMinSize(minHeight = 40.dp),
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 0.8.dp,
            color = CustomColor.Primary.Blue500
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        )
    ) {
        icon?.invoke()
        Spacer(
            modifier = Modifier
                .padding(10.dp)
        )
        Text(
            text = text,
            style = CustomTypography.Label.Large.W600,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 500)
@Composable
fun PreviewButtonSocial() {
    UwangTheme {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                )
                .width(320.dp)
                .height(44.dp)
        ) {
            ButtonSocial(
                modifier = Modifier,
                text = "Masuk Dengan Google",
                enabled = true,
                icon = {
                    Image(
                        painterResource(
                            id = R.drawable.social_icon_google
                        ),
                        contentDescription = "social icon"
                    )
                },
                backgroundColor = Color.White,
                textColor = Gray700,
                onClick = {}
            )
            ButtonSocial(
                modifier = Modifier,
                text = "Masuk Dengan Facebook",
                enabled = true,
                icon = {
                    Image(
                        painterResource(
                            id = R.drawable.social_icon_facebook
                        ),
                        contentDescription = "social icon"
                    )
                },
                backgroundColor = Color(0xFF1877F2),
                textColor = Color.White,
                onClick = {}
            )
            ButtonSocial(
                modifier = Modifier,
                text = "Masuk Dengan Apple",
                enabled = true,
                icon = {
                    Image(
                        painterResource(
                            id = R.drawable.social_icon_apple
                        ),
                        contentDescription = "social icon"
                    )
                },
                backgroundColor = Color.Black,
                textColor = Color.White,
                onClick = {}
            )
        }
    }
}
