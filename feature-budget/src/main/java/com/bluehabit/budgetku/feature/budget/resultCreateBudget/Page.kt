/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.resultCreateBudget

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.UIListenerData
import com.bluehabit.core.ui.UiWrapperData
import com.bluehabit.core.ui.components.ScreenInputFeedback
import com.bluehabit.core.ui.components.ScreenInputSuccess

@Composable
internal fun ScreenResultCreateBudget(
    state: ResultCreateBudgetState = ResultCreateBudgetState(),
    data: ResultCreateBudgetDataState = ResultCreateBudgetDataState(),
    invoker: UIListenerData<ResultCreateBudgetState, ResultCreateBudgetDataState, ResultCreateBudgetEvent>
) = UiWrapperData(invoker) {

    val pagesState = rememberPagerState(
        initialPage = 0
    )



    HorizontalPager(
        pageCount = 2,
        userScrollEnabled = false,
        state = pagesState
    ) {
        when (it) {
            0 -> {
                ScreenInputSuccess(
                    title = stringResource(R.string.text_message_success_create_budget),
                    subtitle = stringResource(R.string.text_message_like_total_budget_create_budget),
                    onDismiss = {
                      //  navigateAndReplaceAll(Home.routeName)
                    },
                    onSubmit = { isLike ->
//                        runSuspend {
//                            pagesState.scrollToPage(
//                                1
//                            )
//                        }

                    }
                )
            }

            1 -> {
                ScreenInputFeedback(
                    title = stringResource(R.string.text_title_like_feedback),
                    feedback = state.feedback,
                    onChange = {
                        commit {
                            copy(
                                feedback = it
                            )
                        }
                    },
                    onSubmit = {
                       // navigateAndReplaceAll(Home.routeName)
                    },
                    onDismiss = {
                       // navigateAndReplaceAll(Home.routeName)
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
        ScreenResultCreateBudget(
            state= ResultCreateBudgetState(),
            data= ResultCreateBudgetDataState(),
            invoker = UIListenerData(
                controller = it,
                state = ResultCreateBudgetState(),
                data= ResultCreateBudgetDataState()
            )
        )
    }
}