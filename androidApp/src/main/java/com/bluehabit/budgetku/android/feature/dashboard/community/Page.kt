/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.community

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.ContentItemFeedTemplate
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.ItemArticle
import com.bluehabit.budgetku.android.components.ItemFeed
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.CreateBudget
import com.bluehabit.budgetku.android.feature.createPost.CreatePost
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransaction
import com.bluehabit.budgetku.android.feature.detailPost.DetailPost
import com.bluehabit.budgetku.android.ui.Grey50
import com.bluehabit.budgetku.android.ui.Grey500
import com.bluehabit.budgetku.android.ui.Grey900
import com.bluehabit.budgetku.data.model.post.PostType

object Community {
    const val routeName = "Community"
}

fun NavGraphBuilder.routeCommunity(
    state: ApplicationState,
) {
    composable(Community.routeName) {
        ScreenCommunity(appState = state)
    }
}

@Composable
internal fun ScreenCommunity(
    appState: ApplicationState,
) = UIWrapper<CommunityViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    with(appState) {
        setupTopAppBar {
            TopAppBarDashboardCommunity(
                selected = state.selectedTab,
                onCreatePost = {
                    navigateSingleTop(CreatePost.routeName)
                },
                onSelectTab = {
                    commit {
                        copy(
                            selectedTab = it
                        )
                    }
                }
            )
        }
        setupBottomSheet {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    hideBottomSheet()
                },
                onAddAccount = {
                    hideBottomSheet()
                    navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
                    hideBottomSheet()
                    navigateSingleTop(CreateTransaction.routeName)
                },
                onAddBudget = {
                    hideBottomSheet()
                    navigateSingleTop(CreateBudget.routeName)
                },
                onAddTransfer = {},
            )
        }
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {
                // remove empty

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {
                showBottomSheet()
            }

        })
    }
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
                        items(dataState.categories.size) {
                            FilterChip(
                                selected = state.selectedCategory == it,
                                onClick = {
                                    commit { copy(selectedCategory = it) }
                                },
                            ) {
                                Text(text = dataState.categories[it])
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
                items(dataState.posts) {
                    when (it.postType) {
                        PostType.TEXT -> ItemFeed(
                            userName = it.displayName,
                            avatar=it.avatar,
                            createdAt = it.date,
                            body = it.body,
                            comments = it.comments,
                            likes = it.likes,
                            onClick = {
                                navigate(DetailPost.routeName,"INIID")
                            }
                        )

                        PostType.BUDGETING_TEMPLATE ->ItemFeed(
                            userName = it.displayName,
                            avatar=it.avatar,
                            createdAt = it.date,
                            comments = it.comments,
                            likes = it.likes,
                            content = {
                                ContentItemFeedTemplate(
                                    items = it.content
                                )
                            },
                            onClick = {
                                navigate(DetailPost.routeName,"INIID")
                            }
                        )

                        PostType.IMAGE -> ItemFeed(
                            userName = it.displayName,
                            avatar=it.avatar,
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
                                navigate(DetailPost.routeName,"INIID")
                            }
                        )

                        else -> Unit
                    }
                }
            }

            if (state.selectedTab == 1) {
                items(dataState.articles) {
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

@Composable
fun TopAppBarDashboardCommunity(
    selected: Int = 0,
    onCreatePost: () -> Unit = {},
    onSelectTab:(Int)->Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_title_dashboard_community),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Grey900
            )
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit_pencil),
                    contentDescription = "",
                    tint = Grey900,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = onCreatePost
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "",
                    tint = Grey900,
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {}
                    )
                )
            }
        }
        TabRow(
            selectedTabIndex = selected,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.primary
        ) {
            Tab(
                selected = true,
                onClick = { onSelectTab(0) },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_feed))
                }
            )
            Tab(
                selected = false,
                onClick = { onSelectTab(1) },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_article))
                }
            )
            Tab(
                selected = false,
                onClick = {
                    onSelectTab(2)
                },
                unselectedContentColor = Grey500,
                text = {
                    Text(text = stringResource(R.string.text_tab_academy))
                }
            )
        }
    }

}

@Preview
@Composable
fun PreviewScreenCommunity() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDashboardCommunity()
        }
    ) {
        ScreenCommunity(it)
    }
}