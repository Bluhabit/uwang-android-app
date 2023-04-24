/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.ItemAccount
import com.bluehabit.budgetku.android.components.button.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Yellow600
import com.bluehabit.budgetku.data.model.account.AccountModel

@Composable
fun ScreenInputSavingAccount(
    selected:Int=0,
    accounts:List<AccountModel> = listOf(),
    onSelectedAccount: (Int) -> Unit = {},
    onAddAccount: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Mau pakai akun yang mana?",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    itemsIndexed(accounts) {index,account->
                        ItemAccount(
                            selected = selected == index,
                            accountBankName = account.accountName,
                            accountBalance = account.accountBalance,
                            onClick = {
                                onSelectedAccount(index)
                            }
                        )
                    }
                })

        }
        Spacer(modifier = Modifier.height(8.dp))

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
                    .border(
                        width = 1.dp,
                        shape = MaterialTheme.shapes.medium,
                        color = Grey300
                    )
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
                        text = stringResource(R.string.text_title_description_tips_create_another_account_create_transaction),
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
                    ButtonOutlinedPrimary(
                        text = stringResource(R.string.text_button_add_account_create_transaction),
                        onClick = onAddAccount
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

    }

}

@Preview
@Composable
fun PreviewScreenInputSavingAccount() {
    BaseMainApp {
        ScreenInputSavingAccount()
    }
}