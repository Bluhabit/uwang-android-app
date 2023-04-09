/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.bottomNavigationListener
import com.bluehabit.budgetku.android.base.extensions.navigateSingleTop
import com.bluehabit.budgetku.android.base.listener.BottomNavigationListener
import com.bluehabit.budgetku.android.components.CardMonthlyBudget
import com.bluehabit.budgetku.android.components.DashboardBottomNavigation
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.HeaderDashboardHome
import com.bluehabit.budgetku.android.components.HeaderSectionDashboardHome
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.components.ItemTransaction
import com.bluehabit.budgetku.android.components.LatestTransactionMonthly
import com.bluehabit.budgetku.android.rememberApplicationState
import java.math.BigDecimal

object Home {
    const val routeName = "Home"
}

fun NavGraphBuilder.routeHome(
    state: ApplicationState
) {
    composable(Home.routeName) {
        ScreenHome(appState = state)

    }
}

@Composable
internal fun ScreenHome(
    appState: ApplicationState
) = UIWrapper<HomeViewModel>(
    appState = appState
) {
    val state by uiState.collectAsState(initial = HomeState())



    with(appState) {
        bottomNavigationListener(object : BottomNavigationListener {
            override fun onRefresh(item: DashboardBottomNavigationMenu) {

            }

            override fun onNavigate(item: DashboardBottomNavigationMenu) {
                navigateSingleTop(item.route)
            }

            override fun onFab() {

            }

        })
    }
    LazyColumn(
        content = {
            item {
                HeaderDashboardHome()
            }
            item {
                HeaderSectionDashboardHome(
                    title = "Daftar Rekening"
                ) {

                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        items(3) {
                            ItemAccount()
                        }
                    })
            }
            item {
                HeaderSectionDashboardHome(
                    title = "Anggaran Bulanan"
                ) {

                }
            }
            item {
                CardMonthlyBudget(
                    transactions = listOf(
                        LatestTransactionMonthly(
                            transactionName = "Makanan",
                            usedAmount = BigDecimal(80_000)
                        ),
                        LatestTransactionMonthly(
                            transactionName = "Kendaraan",
                            usedAmount = BigDecimal(100_000)
                        )
                    )
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = "Transaksi Baru"
                ) {

                }
            }
            items(3) {
                ItemTransaction(
                    transactionName = "McDonald",
                    transactionAccountName = "Bank BCA",
                    transactionCategoryName = "Makanan",
                    transactionDate = "1 April 2023",
                    transactionAmount = "+Rp90.000",
                    transactionType = "Uang Masuk",
                    color = Color(0xFF57C45C)
                )
            }
            item {
                HeaderSectionDashboardHome(
                    title = "Tantangan Keuangan"
                ) {

                }
            }
        }
    )

}


@Preview
@Composable
fun PreviewScreenHome() {
    BaseMainApp(
        bottomBar = {
            DashboardBottomNavigation(currentRoute = Home.routeName)
        }
    ) {
        ScreenHome(rememberApplicationState())
    }

}