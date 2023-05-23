/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.detailPost

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ContentItemFeedTemplate
import com.bluehabit.budgetku.android.components.FormReplyComment
import com.bluehabit.budgetku.android.components.ItemComment
import com.bluehabit.budgetku.data.model.post.PostType

object DetailPost {
    const val routeName = "DetailPost"
    const val argKey = "PostId"
    fun routeName() = "$routeName/{$argKey}"

    val navArg = listOf(
        navArgument(argKey) {
            type = NavType.StringType
        }
    )
}

fun NavGraphBuilder.routeDetailPost(
    state: ApplicationState,
) {
    composable(
        DetailPost.routeName(),
        arguments = DetailPost.navArg
    ) {
        ScreenDetailPost(appState = state)
    }
}

@Composable
internal fun ScreenDetailPost(
    appState: ApplicationState,
) = UIWrapper<DetailPostViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState) {
        setupTopAppBar {
            TopAppBarDetailPost(
                commentCount = dataState.comments.size,
                onBackPressed = {
                    navigateUp()
                }
            )
        }
    }
    LaunchedEffect(key1 = this, block = {
        dispatch(DetailPostEvent.GetDetailPost)
    })
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (dataState.detailPost != null) {
            LazyColumn(content = {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 20.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = dataState.detailPost!!.avatar),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = dataState.detailPost!!.displayName,
                                        style = MaterialTheme.typography.subtitle1,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = dataState.detailPost!!.date,
                                        style = MaterialTheme.typography.body2,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = dataState.detailPost!!.body,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        when (dataState.detailPost!!.postType) {
                            PostType.TEXT -> Unit
                            PostType.BUDGETING_TEMPLATE -> ContentItemFeedTemplate(
                                items = dataState.detailPost!!.content
                            )

                            PostType.IMAGE -> Image(
                                painter = painterResource(id = dataState.detailPost!!.mimeContent),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(335.dp)
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 20.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row {
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_comment),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = dataState.detailPost!!.comments.toString())
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Row {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_like),
                                        contentDescription = ""
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = dataState.detailPost!!.likes.toString())
                                }
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share_feed),
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                items(dataState.comments) {
                    ItemComment(
                        fullName = it.fullName,
                        body = it.body,
                        likes = it.likes,
                        time = it.time,
                        avatar = it.avatar,
                        onLike = {},
                        onReply = {
                            commit {
                                copy(
                                    replyTo = it.fullName
                                )
                            }
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    ),
            ) {
                FormReplyComment(
                    value = state.comment,
                    isReply = state.replyTo.isNotEmpty(),
                    replyTo = state.replyTo,
                    onChange = {
                        commit {
                            copy(
                                comment = it
                            )
                        }
                    }
                )
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun TopAppBarDetailPost(
    commentCount: Int = 0,
    onBackPressed: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.padding(
                    end = 20.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$commentCount Komentar",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = onBackPressed
                )
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun PreviewScreenDetailPost() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDetailPost()
        }
    ) {
        ScreenDetailPost(it)
    }
}