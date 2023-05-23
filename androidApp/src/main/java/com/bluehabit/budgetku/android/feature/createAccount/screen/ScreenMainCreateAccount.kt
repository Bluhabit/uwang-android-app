/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.CardUpgradeToPremium
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey500
import com.bluehabit.budgetku.android.ui.Grey900
import com.bluehabit.budgetku.android.ui.Yellow800
import java.math.BigDecimal

@Composable
fun ScreenMainCreateAccount(
    selectedAccount: String = "",
    amount: String = "",
    onSubscribe: () -> Unit = {},
    onSubmit: () -> Unit = {},
    onInputAmount: () -> Unit = {},
    onInputAccount: () -> Unit = {},
    onBackPressed:()->Unit={}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
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
                    contentDescription = "",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick =onBackPressed
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Tambah Akun Finansialmu",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Yuk, tambahkan akun dan transaksi secara manual, lalu dapatkan informasi yang spesial buat kamu!",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Nama Akun",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = Grey900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(MaterialTheme.shapes.small)
                            .clickable(
                                enabled = true,
                                onClick = onInputAccount
                            )
                            .background(Grey100)
                            .border(
                                width = 1.dp,
                                color = Grey300,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 20.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .padding(
                                        all=6.dp
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),
                                )
                            }
                            Text(
                                text = selectedAccount,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Medium,
                                color = Grey900
                            )
                        }
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Jumlah Saldo",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Normal,
                        color = Grey900
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(MaterialTheme.shapes.small)
                            .clickable(
                                enabled = true,
                                onClick = onInputAmount
                            )
                            .background(Grey100)
                            .border(
                                width = 1.dp,
                                color = Grey300,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(
                                vertical = 8.dp,
                                horizontal = 20.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Rp",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Medium,
                            color = Grey900
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = amount.toString(),
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Normal,
                            color = Grey500
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                CardUpgradeToPremium(
                    onClick = {

                    }
                )
            }
        }
        ButtonPrimary(text = "Simpan", onClick = onSubmit)
    }

}

@Preview
@Composable
fun PreviewScreenMainCreateAccount() {
    BaseMainApp {
        ScreenMainCreateAccount(
            selectedAccount = "Bank BCA",
            amount = "1,000"
        )
    }
}