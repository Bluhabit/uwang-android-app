/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.listAccount

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Chip
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
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
import com.bluehabit.budgetku.android.components.CardItemAccount
import com.bluehabit.budgetku.android.components.Tips
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.ui.Grey200

object ListAccount {
    const val routeName = "ListAccount"
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
                            vertical = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Akun Finansialmu",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = "")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Rp6.000.000",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Chip(onClick = { /*TODO*/ }) {
                        Text(text = "Semua")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Chip(onClick = { /*TODO*/ }) {
                        Text(text = "Tabungan")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Chip(onClick = { /*TODO*/ }) {
                        Text(text = "Investasi")
                    }
                }
            }
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
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 20.dp
                            )
                        ) {
                            CardItemAccount()
                        }
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


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            ButtonPrimary(text = "Tambah Akun")
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