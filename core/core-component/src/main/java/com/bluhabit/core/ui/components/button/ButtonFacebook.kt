/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.components.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluhabit.core.ui.ext.Empty
import com.bluhabit.core.ui.theme.UwangTheme

/**
 * Button Social
 * @param modifier
 * @param text text of the button
 * @sample PreviewButtonFacebook
 * */

@Composable
fun ButtonFacebook(
    modifier: Modifier = Modifier,
    text: String = String.Empty,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    ButtonSocial(
        modifier = modifier,
        text = text,
        enabled = enabled,
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
        onClick = onClick
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, heightDp = 500)
@Composable
fun PreviewButtonFacebook() {
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
            ButtonFacebook(
                modifier = Modifier,
                text = "Masuk Dengan Google",
                enabled = true,
                onClick = {}
            )
            ButtonFacebook(
                modifier = Modifier,
                text = "Masuk Dengan Google",
                enabled = false,
                onClick = {}
            )
        }
    }
}
