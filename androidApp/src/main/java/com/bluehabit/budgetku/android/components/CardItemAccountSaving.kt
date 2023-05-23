/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey700


@Composable
fun CardItemAccountSaving(
    margin: PaddingValues = PaddingValues(),
    icon: Int = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving,
    accountIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    accountName: String = "",
    dueDate: String = "",
    targetSaving: String = "",
    totalSaving: String = "",
    progress: Float = 0f
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(margin)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Grey300
                )
                .background(MaterialTheme.colors.surface)
                .padding(
                    all = 16.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Grey300)
                            .padding(
                                all = 2.dp
                            )
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = accountName,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = "Target tercapai $dueDate",
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Normal,
                            color = Grey700
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(id = accountIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontStyle = MaterialTheme.typography.subtitle2.fontStyle,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSurface
                        )
                    ) {
                        append(totalSaving)
                    }
                    append(" ")
                    append("terkumpul")
                })
                Text(
                    text = "dari $targetSaving",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }

    }

}

@Preview
@Composable
fun PreviewCardItemAccountSaving() {
    BaseMainApp {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            ),
            content = {
                items(3) {
                    CardItemAccountSaving(
                        margin = PaddingValues(
                            horizontal = 20.dp
                        )
                    )
                }
            })
    }
}