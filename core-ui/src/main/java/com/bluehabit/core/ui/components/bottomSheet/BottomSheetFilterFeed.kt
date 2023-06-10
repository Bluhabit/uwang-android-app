/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Blue800
import com.bluehabit.core.ui.theme.Grey900


@Composable
fun BottomSheetFilterFeed(
    onDismiss: () -> Unit = {},
) {
    BaseBottomSheet(
        textConfirmation = stringResource(R.string.button_text_choose),
        enableConfirmation = true,
        showButtonConfirmation = true,
        showLineHeader = false,
        onDismiss = onDismiss,
        onConfirm = {},
        content = {
            Spacer(modifier = Modifier.height(7.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.title_filter_post),
                    color = Grey900,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(133.dp))

                Text(
                    text = stringResource(R.string.button_reset),
                    color = Blue800,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            ItemChooseFilterPost(
                title = stringResource(R.string.subtitle_trending),
                selected = true
            )
            ItemChooseFilterPost(
                title = stringResource(R.string.subtitle_followed)
            )
            ItemChooseFilterPost(
                title = stringResource(R.string.subtitle_allin)
            )
            ItemChooseFilterPost(
                title = stringResource(R.string.subtitle_new)
            )
        }
    )
}

@Composable
fun ItemChooseFilterPost(
    title: String = "",
    selected: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = title,
            color = Grey900,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Normal
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        if (
            selected
        ) {
            Image(painter = painterResource(R.drawable.ceklis_icon), contentDescription = "")
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
    Divider()
    Spacer(modifier = Modifier.height(24.dp))


}


@Preview
@Composable
fun PreviewBottomSheetFilterFeed() {
    BaseMainApp {
        BottomSheetFilterFeed()
    }
}