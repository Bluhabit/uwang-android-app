/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.components.HeaderSection
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.eureka.feature.dashboard.home.DashboardState

@Composable
fun ScreenHome(
    state: DashboardState = DashboardState(),
    onShowBalance:(Boolean)->Unit={},
    onNavigate:(String)->Unit={},
    onNavigateSingleTop:(route:String,params:Array<out String>)->Unit={_,_->}
) {
    LazyColumn(
        content = {
            item {

            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_list_account_dashboard_home),
                    onClick = {
                        onNavigate(Routes.ListAccount.routeName)
                    }
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(4.dp))
                        }

                    })
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_monthly_budgeting_dashboard_home),
                    onClick = {
                        onNavigateSingleTop(Routes.Budget.routeName, arrayOf())
                    }
                )
            }

            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_new_transaction_dashboard_home),
                    onClick = {
                        onNavigateSingleTop(Routes.ListTransaction.routeName, arrayOf())
                    }
                )
            }

            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_challenge_dashboard_home),
                    onClick = {}
                )
            }

            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_tutorial_dashboard_home),
                    onClick = {}
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                )
            }
            item {
                HeaderSection(
                    title = stringResource(R.string.title_section_article_dashboard_home)
                ) {

                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp {
        ScreenHome()
    }
}