/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.trian.mvi.ui.extensions.formatToRupiah
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey700
import java.math.BigDecimal

@Composable
fun BottomSheetAccount(
    selectedAccount:String="",
    accounts:List<AccountModel> = listOf(),
    onSelectedAccount:(AccountModel) -> Unit={},
    onSubmit:()->Unit={}
) {
    BaseBottomSheet(
        textConfirmation = "Simpan",
        onConfirm = onSubmit
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            ),
            content = {
                items(accounts) {
                    ItemBottomSheetAccount(
                        selected = selectedAccount == it.id,
                        accountBankName = it.accountName,
                        accountBankIcon=it.icon,
                        balance = it.accountBalance,
                        onClick = {
                            onSelectedAccount(it)
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun ItemBottomSheetAccount(
    selected: Boolean = false,
    accountBankName: String = "",
    accountBankIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    balance: BigDecimal = BigDecimal.ZERO,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .border(
                width = 1.dp,
                color = if (selected) MaterialTheme.colors.primary else Grey300,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                all = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Image(
                    painter = painterResource(id = accountBankIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                )
            }
            Text(
                text = accountBankName,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = Grey700
            )
        }
        Text(
            text = balance.formatToRupiah(),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
    }

}

@Preview
@Composable
fun PreviewBottomSheetAccount() {
    BaseMainApp {
        BottomSheetAccount()
    }
}