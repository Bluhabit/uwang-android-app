/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.listAccount.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.data.model.account.AccountSavingModel
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.components.CardItemAccountSaving
import com.bluehabit.core.ui.components.Tips
import com.bluehabit.core.ui.theme.Grey200

@Composable
fun ScreenSaving(
    state: LazyListState = rememberLazyListState(),
    savings: List<AccountSavingModel> = listOf()
) {
    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Grey200)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Akun Manual",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colors.onSurface
                    )
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowUp,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
            items(savings) {
                CardItemAccountSaving(
                    margin = PaddingValues(
                        horizontal = 20.dp
                    ),
                    accountIcon = it.accountIcon,
                    icon = it.icon,
                    accountName = it.savingName,
                    dueDate = it.target.toString(),
                    targetSaving = it.targetBalance,
                    totalSaving = it.totalBalance,
                    progress = it.progress
                )
            }
            item {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp
                    )
                ) {
                    Tips(
                        title = "Tips",
                        message = "Sedikit demi sedikit lama-lama jadi bukit, mulai " +
                                "menabung setiap hari atau setiap bulan untuk capai target keuanganmu!"
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    )
}

@Preview
@Composable
fun PreviewScreenSaving() {
    BaseMainApp {
        ScreenSaving()
    }
}

