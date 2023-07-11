/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bluehabit.eureka.data.model.post.PostType
import com.bluehabit.eureka.feature.dashboard.home.DashboardState
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.ContentItemFeedTemplate
import com.bluehabit.core.ui.components.ItemArticle
import com.bluehabit.core.ui.components.ItemFeed
import com.bluehabit.core.ui.theme.Grey50
import com.bluehabit.core.ui.theme.Grey900

@Composable
fun ScreenCommunity(
    state: DashboardState = DashboardState(),
    onSelectCategory:(Int)->Unit={}
) {
    LazyColumn(
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 16.dp
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .background(Grey50)
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cari postingan"
                        )
                    }

                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(
                            fraction = 0.9f
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp
                        )
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        items(state.categories.size) {
                            FilterChip(
                                selected = state.selectedCategory == it,
                                onClick = {
                                    onSelectCategory(it)
                                },
                            ) {
                                Text(text = state.categories[it])
                            }
                        }
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_circle),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            )
                    )

                }
            }
            if (state.selectedTab == 0) {
                items(state.posts) {
                    when (it.postType) {
                        PostType.TEXT -> ItemFeed(
                            userName = it.displayName,
                            avatar = it.avatar,
                            createdAt = it.date,
                            body = it.body,
                            comments = it.comments,
                            likes = it.likes,
                            onClick = {
                                // navigate(DetailPost.routeName,it.id)
                            }
                        )

                        PostType.BUDGETING_TEMPLATE -> ItemFeed(
                            userName = it.displayName,
                            avatar = it.avatar,
                            createdAt = it.date,
                            comments = it.comments,
                            likes = it.likes,
                            content = {
                                ContentItemFeedTemplate(
                                    items = it.content
                                )
                            },
                            onClick = {
                                // navigate(DetailPost.routeName,it.id)
                            }
                        )

                        PostType.IMAGE -> ItemFeed(
                            userName = it.displayName,
                            avatar = it.avatar,
                            createdAt = it.date,
                            comments = it.comments,
                            likes = it.likes,
                            content = {
                                Text(
                                    text = it.body,
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Normal,
                                    color = Grey900
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Image(
                                    painter = painterResource(id = it.mimeContent),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.FillWidth
                                )
                            },
                            onClick = {
                                //  navigate(DetailPost.routeName,it.id)
                            }
                        )

                        else -> Unit
                    }
                }
            }

            if (state.selectedTab == 1) {
                items(state.articles) {
                    ItemArticle(
                        title = it.title,
                        createdAt = it.date,
                        likes = it.likes,
                        image = it.image
                    )
                }
            }
        }
    )
}