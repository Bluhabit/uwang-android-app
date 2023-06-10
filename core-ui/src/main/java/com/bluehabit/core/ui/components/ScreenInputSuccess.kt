/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300

@Composable
fun ScreenInputSuccess(
    title: String = "",
    subtitle: String = "",
    showCloseButton:Boolean=true,
    onDismiss: () -> Unit = {},
    onSubmit: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_overlay_result_create_budget),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = 0.4f
                ),
            contentScale = ContentScale.FillWidth
        )
        if(showCloseButton) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = onDismiss
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = ""
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(
                    fraction = 0.8f
                )
                .align(
                    Alignment.Center
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        Color(0xFFEBFFEC)
                    )
                    .padding(
                        all = 40.dp
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_circle),
                    contentDescription = "",
                    tint = Color(0xFF57C45C)
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 60.dp
                    ),
                textAlign = TextAlign.Center
            )
            DottedLine(
                color = Grey300,
                height = 5.dp
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 60.dp
                    ),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Grey300
                        )
                        .clickable(
                            enabled = true,
                            onClick = {
                                onSubmit(true)
                            }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_thumb_up),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Grey300
                        )
                        .clickable(
                            enabled = true,
                            onClick = {
                                onSubmit(false)
                            }
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_thumb_down),
                        contentDescription = ""
                    )
                }
            }
        }


    }


}

@Preview
@Composable
fun PreviewScreenSuccessCreateBudget() {
    BaseMainApp {
        ScreenInputSuccess()
    }
}