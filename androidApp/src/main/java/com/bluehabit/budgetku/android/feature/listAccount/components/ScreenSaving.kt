/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.CardItemAccountSaving
import com.bluehabit.budgetku.android.components.Tips
import com.bluehabit.budgetku.android.ui.Grey200

@Composable
fun ScreenSaving() {
    LazyColumn(
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
            items(3) {
                CardItemAccountSaving(
                    margin = PaddingValues(
                        horizontal = 20.dp
                    )
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
                        message = "Dengan hubungkan akun finansial budgetku bisa" +
                                " analisa keuanganmu lebih lengkap, " +
                                "bantu kamu atur budget dan capai target keuanganmu dengan mudah!"
                    )
                }
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

