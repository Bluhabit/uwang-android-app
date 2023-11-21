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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.extensions.from
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.theme.GaweanTheme
import com.bluhabit.core.ui.theme.Gray300
import com.bluhabit.core.ui.theme.Gray700

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
    val ctx = LocalContext.current
    Button(
        modifier = modifier
            .height(44.dp.from(context = ctx))
            .defaultMinSize(minHeight = 44.dp.from(context = ctx))
        ,
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Gray300
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
                .padding(12.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 500)
@Composable
fun PreviewButtonSocial() {
    GaweanTheme {
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
                backgroundColor =  Color(0xFF1877F2),
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
