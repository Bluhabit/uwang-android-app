/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.resultCreateBudget

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.feature.dashboard.home.Home
import com.bluehabit.budgetku.android.feature.resultCreateBudget.components.ScreenFeedbackCreateBudget
import com.bluehabit.budgetku.android.feature.resultCreateBudget.components.ScreenSuccessCreateBudget
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

object ResultCreateBudget {
    const val routeName = "ResultCreateBudget"
}

fun NavGraphBuilder.routeResultCreateBudget(
    state: ApplicationState,
) {
    composable(ResultCreateBudget.routeName) {
        ScreenResultCreateBudget(appState = state)
    }
}

@Composable
internal fun ScreenResultCreateBudget(
    appState: ApplicationState,
) = UIWrapper<ResultCreateBudgetViewModel>(appState = appState) {
    val pagesState = rememberPagerState(
        initialPage = 0
    )

    HorizontalPager(
        count = 2,
        userScrollEnabled = false,
        state = pagesState
    ) {
        when (it) {
            0 -> {
                ScreenSuccessCreateBudget(
                    onDismiss = {
                        navigateAndReplaceAll(Home.routeName)
                    },
                    onSubmit = { isLike ->
                        runSuspend {
                            pagesState.scrollToPage(
                                1
                            )
                        }

                    }
                )
            }

            1 -> {
                ScreenFeedbackCreateBudget(
                    feedback = "",
                    onChange = {},
                    onSubmit = {
                        navigateAndReplaceAll(Home.routeName)
                    },
                    onDismiss = {
                        navigateAndReplaceAll(Home.routeName)
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenResultCreateBudget() {
    BaseMainApp {
        ScreenResultCreateBudget(it)
    }
}