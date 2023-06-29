/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.theme.BudgetKuTheme

@Composable
fun ItemComment(
    avatar: Int = com.bluehabit.budgetku.data.R.drawable.dummy_avatar,
    fullName: String = "",
    time: String = "",
    body: String = "",
    likes: Int = 0,
    onLike: () -> Unit = {},
    onReply: () -> Unit = {},
    onOptionMenu: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
           Row(
               modifier = Modifier,
               horizontalArrangement = Arrangement.Start,
               verticalAlignment = Alignment.Top
           ) {
               Image(
                   painter = painterResource(id = avatar),
                   contentDescription = "",
                   modifier = Modifier
                       .size(28.dp)
                       .clip(CircleShape),
                   contentScale = ContentScale.FillBounds
               )
               Spacer(modifier = Modifier.width(8.dp))
               Column(
                   modifier = Modifier.fillMaxWidth(
                       fraction = 0.8f
                   )
               ) {
                   Row(
                       modifier = Modifier,
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.Start
                   ) {
                       Text(
                           text = fullName,
                           style = MaterialTheme.typography.subtitle1,
                           fontWeight = FontWeight.Bold
                       )
                       Spacer(modifier = Modifier.width(8.dp))
                       Icon(
                           painter = painterResource(id = R.drawable.ic_dot),
                           contentDescription = ""
                       )
                       Spacer(modifier = Modifier.width(8.dp))
                       Text(
                           text = time,
                           style = MaterialTheme.typography.subtitle2,
                           fontWeight = FontWeight.Normal
                       )
                   }
                   Spacer(modifier = Modifier.height(8.dp))
                   Text(
                       text = body,
                       style = MaterialTheme.typography.body1,
                       fontWeight = FontWeight.Normal
                   )
                   Spacer(modifier = Modifier.height(16.dp))
                   Row(
                       horizontalArrangement = Arrangement.Start,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       if (likes == 0) {
                           Text(
                               text = "Suka",
                               style = MaterialTheme.typography.body2,
                               fontWeight = FontWeight.Normal,
                               modifier = Modifier.clickable(
                                   enabled = true,
                                   onClick = onLike
                               )
                           )
                       } else {
                           Row(
                               modifier = Modifier.clickable(
                                   enabled = true,
                                   onClick = onLike
                               ),
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Icon(
                                   painter = painterResource(id = R.drawable.ic_like),
                                   contentDescription = ""
                               )
                               Spacer(modifier = Modifier.width(4.dp))
                               Text(
                                   text = likes.toString(),
                                   style = MaterialTheme.typography.body2,
                                   fontWeight = FontWeight.Normal
                               )
                           }
                       }
                       Spacer(modifier = Modifier.width(16.dp))
                       Text(
                           text = "Balas",
                           style = MaterialTheme.typography.body2,
                           fontWeight = FontWeight.Normal,
                           modifier = Modifier.clickable(
                               enabled = true,
                               onClick = onReply
                           )
                       )
                   }
               }
           }
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert_horizontal),
                contentDescription = "",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onOptionMenu
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun PreviewItemComment() {
    BudgetKuTheme {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            ItemComment(
                fullName = "Trian Damai",
                body = "Harus atur budgeting tuh kak supaya gak boncos",
                likes = 1,
                time = "23 Menit lalu"
            )
        }
    }
}