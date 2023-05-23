/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccount.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.appendButton
import com.bluehabit.budgetku.android.components.ItemTemplateAccount
import com.bluehabit.budgetku.android.components.alert.Alert
import com.bluehabit.budgetku.android.components.alert.AlertDefaults
import com.bluehabit.budgetku.android.components.input.FormInputSearch
import com.bluehabit.budgetku.android.ui.Blue50
import com.bluehabit.budgetku.data.model.CategoryFinancialAccountModel

@Composable
fun ScreenInputAccount(
    accounts:List<CategoryFinancialAccountModel> = listOf(),
    onSelect: () -> Unit = {},
    onBackPressed:()->Unit={}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                end = 20.dp
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
                        onClick = onBackPressed
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
            LazyColumn(
                content = {
                    item {
                        FormInputSearch(
                            value = "",
                            onChange = {},
                            placeholder = "Cari akun"
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Alert(
                            icon = R.drawable.ic_idea_bulb,
                            text = buildAnnotatedString {
                                append("Yuk, hubungkan semua akun finansialmu! Mau tau keuntungannya? cek")
                                appendButton(
                                    text = "di sini",
                                    tag = "tag"
                                )
                            },
                            variant = AlertDefaults.flat(
                                backgroundColor = Blue50
                            ),
                            tag = "tag",
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    items(accounts){
                        ItemTemplateAccount(
                            accountName = it.name,
                            items=it.children,
                            onSelectedAccount = onSelect
                        )
                    }
                }
            )

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