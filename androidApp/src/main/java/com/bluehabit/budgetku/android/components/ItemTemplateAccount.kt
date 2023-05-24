/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey700
import com.bluehabit.budgetku.android.ui.Yellow50
import com.bluehabit.budgetku.data.model.FinancialAccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyFinancialAccount

@Composable
fun ItemTemplateAccount(
    accountName: String = "",
    items: List<FinancialAccountModel> = listOf(),
    onSelectedAccount: (FinancialAccountModel) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = accountName,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Normal,
            color = Grey700,
            modifier = Modifier.padding(
                vertical = 8.dp
            )
        )
        items.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = {
                            onSelectedAccount(it)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = it.icon),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                }
                Text(
                    text = "Manual",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Grey700,
                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.medium
                        )
                        .background(Yellow50)
                        .padding(
                            all = 8.dp
                        )
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewItemTemplateAccount() {
    BaseMainApp {
        LazyColumn(content = {
            items(dummyFinancialAccount) {
                ItemTemplateAccount(
                    accountName = it.name,
                    items = it.children
                )
            }
        })
    }
}