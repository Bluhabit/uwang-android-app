/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import com.bluehabit.budgetku.android.ui.Blue600
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500
import com.bluehabit.budgetku.android.ui.Grey700
import com.bluehabit.budgetku.android.ui.Grey900
import com.bluehabit.budgetku.data.model.post.ContentBudgetingPostModel

@Composable
fun ItemFeed(
    userName: String = "",
    createdAt: String = "",
    comments: Int = 0,
    likes: Int = 0,
    verified: Boolean = false,
    onClick: () -> Unit = {},
    onComment: () -> Unit = {},
    onLike: () -> Unit = {},
    onShare: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = onClick
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = com.bluehabit.budgetku.data.R.drawable.dummy_avatar),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row {
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                    if (verified) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_verified_check_bold),
                            contentDescription = "",
                            tint = Blue600
                        )
                    }
                }
                Text(
                    text = createdAt,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal,
                    color = Grey700
                )
            }
        }
        Column(
            modifier = Modifier.padding(
                horizontal = 20.dp
            )
        ) {
            content.invoke()
        }
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
            Row(
                modifier = Modifier.fillMaxWidth(
                    fraction = 0.8f
                ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(
                        fraction = 0.3f
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "",
                        tint = Grey500,
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = onComment
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = comments.toString(),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = Grey500
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(
                        fraction = 0.3f
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "",
                        tint = Grey500,
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = onLike
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = likes.toString(),
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Normal,
                        color = Grey500
                    )

                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_share_feed),
                contentDescription = "",
                tint = Grey500,
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onShare
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
    }
}

@Composable
fun ItemFeed(
    userName: String = "",
    createdAt: String = "",
    body: String = "",
    comments: Int = 0,
    likes: Int = 0,
    verified: Boolean = false,
    onClick: () -> Unit={},
    onComment: () -> Unit = {},
    onLike: () -> Unit = {},
    onShare: () -> Unit = {},
) {
    ItemFeed(
        userName,
        createdAt,
        comments,
        likes,
        verified,
        onClick,
        onComment,
        onLike,
        onShare
    ) {
        Text(
            text = body,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = Grey900
        )
    }
}


@Composable
fun ContentItemFeedTemplate(
    items: List<ContentBudgetingPostModel> = listOf()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                MaterialTheme.shapes.medium
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = Grey300
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                all = 16.dp
            )
    ) {
        items.forEachIndexed { index, feed ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(
                        fraction = 0.4f
                    )
                ) {
                    Image(
                        painter = painterResource(id = feed.icon),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feed.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = Grey700
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = feed.allocation,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal,
                        color = Grey700
                    )
                    Text(
                        text = feed.amount.formatToRupiah(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Medium,
                        color = Grey900
                    )
                }
            }
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}

@Preview
@Composable
fun PreviewItemFeed() {
    BaseMainApp {
        LazyColumn(
            content = {

            }
        )
    }
}