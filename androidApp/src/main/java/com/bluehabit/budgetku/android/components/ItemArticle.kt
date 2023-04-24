/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey700

@Composable
fun ItemArticle(
    title: String = "",
    createdAt: String = "",
    likes: Int = 0,
    image:Int=R.drawable.ic_dummy_article_4
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(
                    fraction = 0.6f
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = createdAt,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )

            }
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .width(120.dp)
                    .height(80.dp),
                contentScale = ContentScale.FillWidth
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(
                    fraction = 0.4f
                ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = likes.toString())
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_share_feed),
                    contentDescription = ""
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Preview
@Composable
fun PreviewItemArticle() {
    BaseMainApp {
        LazyColumn(content = {
            items(4) {
                ItemArticle(
                    title = "6 Cara Mengature Keuangan Pribadi yang MUdah dan Gak Ribet!",
                    createdAt = "11 April 2023, 22:54",
                    likes = 20
                )
            }
        })
    }
}