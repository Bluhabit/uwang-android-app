/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Yellow600
import java.math.BigDecimal

@Composable
fun ScreenInputAccount(
    transactionType: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.primary
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Mau pakai akun yang mana buat $transactionType kamu?",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    items(3) {
                        ItemAccount(
                            selected = it == 1,
                            accountBankName = "Bank BCA",
                            accountBalance = BigDecimal(1_000),
                        )
                    }
                })

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            all = 20.dp
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Grey100),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_idea_star),
                            contentDescription = "",
                            tint = Yellow600
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Kamu juga bisa tambah akun rekening lainnya dan bisa langsung dipakai transaksi",
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal
                    )
                }
                Divider()

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        )
                ) {
                    ButtonOutlinedPrimary(text = "Tambah Rekening Disini")
                }
            }
        }

    }

}

@Preview
@Composable
fun PreviewScreenInputAccount() {
    BaseMainApp {
        ScreenInputAccount()
    }
}