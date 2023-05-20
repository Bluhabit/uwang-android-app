/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components.bottomSheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.ui.BudgetKuTheme

data class ItemBottomSheetAccount(
    val icon: Int,
    val name: String,
    val value: String,
    val isSelected: Boolean,
)

@Composable
fun BottomSheetSelectAccount(
    accounts: List<ItemBottomSheetAccount> = listOf(),
    onDismiss: () -> Unit = {},
    onSelected: (String) -> Unit = {},
) {
    val selectedAccount = remember { mutableStateOf("") }
    BaseBottomSheet(
        textConfirmation = stringResource(id = R.string.text_bottom_sheet_account_confirm),
        onDismiss = onDismiss,
        onConfirm = { onSelected(selectedAccount.value) }
    ) {
        LazyColumn {
            item {
                Text(
                    text = stringResource(id = R.string.text_bottom_sheet_account),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    accounts.forEach {
                        ItemBottomSheetSelectAccount(
                            icon = it.icon,
                            name = it.name,
                            value = it.value,
                            onSelected = { account ->
                                 selectedAccount.value = account
                            },
                            isSelected = it.isSelected
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ItemBottomSheetSelectAccount(
    icon: Int,
    name: String = "",
    value: String = "",
    onSelected: (String) -> Unit = {},
    isSelected: Boolean = true,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(1.5.dp, if (!isSelected) Color(0xFFe0e0e0) else Color(0xFF1962fe)),
                RoundedCornerShape(16.dp)
            )
            .clickable {
                onSelected(name)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFf5f5f5)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = icon),
                contentDescription = ""
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Preview
@Composable
fun PreviewBottomSheetSelectAccount() {
    BudgetKuTheme {
        BottomSheetSelectAccount(
            listOf(
                ItemBottomSheetAccount(
                    icon = R.drawable.ic_account_bca,
                    name = stringResource(id = R.string.text_bank_bca),
                    value = "Rp.1000.000",
                    isSelected = true,
                ),
                ItemBottomSheetAccount(
                    icon = R.drawable.ic_account_bca,
                    name = stringResource(id = R.string.text_bank_bca),
                    value = "Rp.1000.000",
                    isSelected = false,
                ),
                ItemBottomSheetAccount(
                    icon = R.drawable.ic_account_jago,
                    name = stringResource(id = R.string.text_bank_jago),
                    value = "Rp.400.000",
                    isSelected = false,
                ),
            )
        )
    }
}