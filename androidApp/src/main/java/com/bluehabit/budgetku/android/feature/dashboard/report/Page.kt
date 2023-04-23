/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.report

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ChipDefaults
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.bluehabit.budgetku.android.components.DashboardBottomNavigationMenu
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetAddBudgetTransaction
import com.bluehabit.budgetku.android.components.chart.PieChart
import com.bluehabit.budgetku.android.feature.createAccount.CreateAccount
import com.bluehabit.budgetku.android.feature.createBudget.CreateBudget
import com.bluehabit.budgetku.android.feature.createTransaction.CreateTransaction
import com.bluehabit.budgetku.android.ui.Grey200
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500


object Report {
    const val routeName = "Report"
}

fun NavGraphBuilder.routeReport(
    state: ApplicationState,
) {
    composable(Report.routeName) {
        ScreenReport(appState = state)
    }
}

@Composable
internal fun ScreenReport(
    appState: ApplicationState,
) = UIWrapper<ReportViewModel>(appState = appState) {
    with(appState) {
        setupTopAppBar {
            TopAppBarDashboardReport()
        }
        setupBottomSheet {
            BottomSheetAddBudgetTransaction(
                onDismiss = {
                    hideBottomSheet()
                },
                onAddAccount = {
                    navigateSingleTop(CreateAccount.routeName)
                },
                onAddTransaction = {
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

    LazyColumn(content = {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Bagaimana alokasi Keuanganmu?")
                Row(
                    modifier = Modifier.fillMaxWidth().padding(
                        horizontal = 20.dp
                    ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilterChip(
                        selected = true,
                        enabled = true,
                        onClick = {
                        },
                        colors = ChipDefaults.outlinedFilterChipColors(
                            backgroundColor = Grey200,
                            contentColor = Grey500,
                            selectedBackgroundColor = MaterialTheme.colors.primary,
                            selectedContentColor = MaterialTheme.colors.onPrimary
                        )
                    ) {
                        Text(
                            text = "Pemasukkan"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    FilterChip(
                        selected = false,
                        enabled = true,
                        onClick = {
                        },
                        colors = ChipDefaults.outlinedFilterChipColors(
                            backgroundColor = Grey200,
                            contentColor = Grey500,
                            selectedBackgroundColor = MaterialTheme.colors.primary,
                            selectedContentColor = MaterialTheme.colors.onPrimary
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Grey300
                        )
                    ) {
                        Text(
                            text = "Pemasukkan"
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = ""
                    )
                }
//                AndroidView(factory = {
//                    PieChart(it).apply {
//                        val pieEntries = mutableListOf<PieEntry>()
//
//                        val label="Type"
//
//                        //init data
//                        val typeAmountMap: MutableMap<String, Int> = HashMap()
//                        typeAmountMap["Toys"] = 200
//                        typeAmountMap["Snacks"] = 230
//                        typeAmountMap["Clothes"] = 100
//                        typeAmountMap["Stationary"] = 500
//                        typeAmountMap["Phone"] = 50
//
//                        //init color
//                        val colors = ArrayList<Int>()
//                        colors.add(Color.parseColor("#304567"))
//                        colors.add(Color.parseColor("#309967"))
//                        colors.add(Color.parseColor("#476567"))
//                        colors.add(Color.parseColor("#890567"))
//                        colors.add(Color.parseColor("#a35567"))
//                        colors.add(Color.parseColor("#ff5f67"))
//                        colors.add(Color.parseColor("#3ca567"))
//
//                        //input data and fit into pieChart
//                        for (type in typeAmountMap.keys) {
//                            pieEntries.add(PieEntry(typeAmountMap[type]?.toFloat() ?: 0f, type))
//                        }
//
//                        //collecting the entries with label name
//                        //collecting the entries with label name
//                        val pieDataSet = PieDataSet(pieEntries, label)
//                        //setting text size of the value
//                        //setting text size of the value
//                        pieDataSet.valueTextSize = 12f
//                        //providing color list for coloring different entries
//                        //providing color list for coloring different entries
//                        pieDataSet.colors = colors
//                        //grouping the data set from entry to chart
//                        //grouping the data set from entry to chart
//                        val pieData = PieData(pieDataSet)
//                        //showing the value of the entries, default true if not set
//                        //showing the value of the entries, default true if not set
//                        pieData.setDrawValues(true)
//
//                        data = pieData
//                        invalidate()
//                    }
//                })

                PieChart(
                    modifier = Modifier.padding(
                        horizontal = 20.dp
                    ),
                    progress = listOf(
                        60f,
                        40f
                    ),
                    colors = listOf(
                        androidx.compose.ui.graphics.Color.Blue,
                        androidx.compose.ui.graphics.Color.Cyan
                    ),
                    isDonut = true
                )
            }
        }
    })
}

@Composable
fun TopAppBarDashboardReport() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    40.dp
                )
                .padding(
                    horizontal = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Laporan Keuanganmu",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = ""
            )
        }
        TabRow(
            selectedTabIndex = 0,
            backgroundColor = MaterialTheme.colors.surface,
            modifier = Modifier.height(
                44.dp
            )
        ) {
            Tab(selected = true, onClick = { /*TODO*/ }) {
                Text(
                    text = "Laporan",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium
                )
            }
            Tab(selected = false, onClick = { /*TODO*/ }) {
                Text(
                    text = "Saran",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenReport() {
    BaseMainApp(
        topAppBar = {
            TopAppBarDashboardReport()
        }
    ) {
        ScreenReport(it)
    }
}