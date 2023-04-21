/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.createBudget.components.ScreenNumPad
import com.bluehabit.budgetku.android.feature.createBudget.components.ScreenInputAmountBudget
import com.bluehabit.budgetku.android.feature.resultCreateBudget.ResultCreateBudget
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

object CreateBudget {
    const val routeName = "CreateBudget"
}

fun NavGraphBuilder.routeCreateBudget(
    state: ApplicationState,
) {
    composable(CreateBudget.routeName) {
        ScreenCreateBudget(appState = state)
    }
}

@Composable
internal fun ScreenCreateBudget(
    appState: ApplicationState,
) = UIWrapper<CreateBudgetViewModel>(appState = appState) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    with(appState) {
        setupTopAppBar {
            TopAppBar(
                elevation = 0.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navigateUp()
                        }
                    )
                },
                title = {
                    Text(
                        text = stringResource(R.string.text_title_page_create_budget),
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }

    HorizontalPager(
        count = 2,
        state = pagerState,
        userScrollEnabled = false
    ) {
        when (it) {
            0 -> {
                appState.showTopAppBar()
                ScreenInputAmountBudget(
                    amount = "",
                    onInputAmount = {
                        runSuspend {
                            pagerState.scrollToPage(
                                page = 1
                            )
                        }

                    },
                    onSubmit = {
                        navigateAndReplaceAll(ResultCreateBudget.routeName)
                    }
                )
            }

            1 -> {
                appState.hideTopAppBar()
                ScreenNumPad(
                    value = "",
                    onSubmit = {
                        runSuspend {
                            pagerState.scrollToPage(
                                page = 0
                            )
                        }
                    },
                    onClear = {},
                    onRemove = {},
                    onDismiss = {
                        runSuspend {
                            pagerState.scrollToPage(
                                page = 0
                            )
                        }
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewScreenCreateBudget() {
    BaseMainApp(
        topAppBar = {
            TopAppBar(
                elevation = 0.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = ""
                    )
                },
                title = {
                    Text(
                        text = "Atur Total Budget",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) {
        ScreenCreateBudget(it)
    }
}