/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ChipDefaults
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.feature.createAccountSaving.CreateAccountSaving
import com.bluehabit.budgetku.android.feature.listAccount.components.ScreenAccount
import com.bluehabit.budgetku.android.feature.listAccount.components.ScreenSaving
import com.bluehabit.budgetku.android.ui.Grey200
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500

object ListAccount {
    const val routeName = "ListAccount"

    val tabs = listOf(
        "Semua",
        "Tabungan",
        "Investasi"
    )
}

fun NavGraphBuilder.routeListAccount(
    state: ApplicationState,
) {
    composable(ListAccount.routeName) {
        ScreenListAccount(appState = state)
    }
}

@Composable
internal fun ScreenListAccount(
    appState: ApplicationState,
) = UIWrapper<ListAccountViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    val dataState by uiDataState.collectAsState()

    val scrollState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex == 0 }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.surface
                    )
                    .padding(
                        horizontal = 20.dp
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = {
                                navigateUp()
                            }
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Akun Finansialmu",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Saldo",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = dataState.balance.formatToRupiah(),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListAccount.tabs.forEachIndexed { index, s ->
                        FilterChip(
                            selected = state.selectedTab == index,
                            enabled = true,
                            onClick = {
                                if(index != ListAccount.tabs.size -1 ){
                                    commit { copy(selectedTab = index) }
                                }
                            },
                            colors = ChipDefaults.outlinedFilterChipColors(
                                backgroundColor = Grey200,
                                contentColor = Grey500,
                                selectedBackgroundColor = MaterialTheme.colors.primary,
                                selectedContentColor = MaterialTheme.colors.onPrimary
                            ),
                            border = if (index != state.selectedTab) BorderStroke(
                                width = 1.dp,
                                color = Grey300
                            ) else null,
                        ) {
                            Text(
                                text = s
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            when (state.selectedTab) {
                0 -> ScreenAccount(
                    state = scrollState,
                    accounts = dataState.accounts
                )

                1 -> ScreenSaving(
                    state = scrollState,
                    savings = dataState.savingAccounts
                )
            }
        }

        if (showButton) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 16.dp
                    )
            ) {
                ButtonPrimary(
                    text = "Tambah Akun",
                    onClick = {
                        navigateSingleTop(CreateAccountSaving.routeName)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenListAccount() {
    BaseMainApp {
        ScreenListAccount(it)
    }
}