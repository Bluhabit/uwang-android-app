/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Grey300
import com.bluehabit.core.ui.theme.Grey500
import com.bluehabit.core.ui.theme.Grey700
import java.math.BigDecimal

@Composable
fun CardItemAccount(
    margin: PaddingValues = PaddingValues(),
    icon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    accountName: String = "",
    accountBalance: BigDecimal = BigDecimal.ZERO,
    hasSaving: Boolean = false,
    savingIcon: Int = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving,
    savingName: String = ""
) {
    Column(
        modifier = Modifier.padding(
            margin
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.small
                )
                .border(
                    width = 1.dp,
                    shape = MaterialTheme.shapes.small,
                    color = Grey300
                )
                .padding(
                    all = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(
                            all = 6.dp
                        )
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp),
                    )
                    if (hasSaving) {
                        Image(
                            painter = painterResource(id = savingIcon),
                            contentDescription = "",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(
                                    18.dp
                                )
                                .clip(CircleShape),
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = accountName,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Medium,
                        color = Grey700
                    )
                    if (hasSaving) {
                        Text(
                            text = savingName,
                            style = MaterialTheme.typography.subtitle2,
                            fontWeight = FontWeight.Medium,
                            color = Grey500
                        )
                    }
                }

            }
            Text(
                text = accountBalance.formatToRupiah(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardItemAccount() {
    BaseMainApp {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                16.dp
            ),
            content = {
                items(2) {
                    CardItemAccount()
                }
            })
    }
}