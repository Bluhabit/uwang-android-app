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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey700
import com.bluehabit.core.ui.theme.Pink500


@Composable
fun CardChallengeBudgeting(
    title: String = "",
    message: String = "",
    point: Int = 0,
    pointTarget: Int = 0,
    progress:Float=0f,
    color: Color= Color.Green,
    textColor: Color= MaterialTheme.colors.onPrimary,
    image: Int = 0,
    clickable: Boolean = true,
    margin:PaddingValues= PaddingValues(),
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.padding(margin)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    color = Grey300,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color
                    )
                    .padding(
                        vertical = 16.dp,
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth(
                            fraction = 0.8f
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 16.dp
                        )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = message,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal,
                        color = textColor,
                        softWrap = true
                    )
                }
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.BottomEnd)
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth(
                            fraction = 0.6f
                        )
                        .height(16.dp),
                    backgroundColor = Grey300,
                    color = color,
                    strokeCap = StrokeCap.Round
                )

                Text(
                    text = stringResource(id = R.string.text_label_points_dashboard_home, point.toString(), pointTarget.toString()),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    color = Grey700
                )
            }
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = clickable,
                        onClick = onClick
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.text_button_show_challenge_card_challenge_budgeting),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Grey700
                )
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardChallengeBudgeting() {
    BaseMainApp {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(2) {
                    CardChallengeBudgeting(
                        title = "Follow the money trail",
                        message = "Answer",
                        image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_challenge,
                        point = 1823,
                        pointTarget = 2000,
                        color = Pink500,
                        progress = 0.4f,
                        onClick = {}
                    )
                }
            }
        )
    }
}