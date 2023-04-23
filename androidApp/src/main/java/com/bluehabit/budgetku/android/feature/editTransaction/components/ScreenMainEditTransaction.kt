/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.button.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.input.FormInput

@Composable
fun ScreenMainEditTransaction() {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 40.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.primary
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        MaterialTheme.colors.surface
                    )
                    .padding(
                        all = 16.dp
                    )
            ) {
                Text(
                    text = "Transaksi",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .width(cardWidth)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(0xFF1952CE))
                            .padding(
                                vertical = 8.dp
                            )
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.arrow_long_circle_up),
                            contentDescription = "",
                            tint = Color(0xFF57C45C)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pemasukan",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Row(
                        modifier = Modifier
                            .width(cardWidth)
                            .clip(MaterialTheme.shapes.small)
                            .background(Color(0xFF1952CE))
                            .padding(
                                vertical = 8.dp
                            )
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_long_circle_down),
                            contentDescription = "",
                            tint = Color(0xFFFE3419)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Pengeluaran",
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Detail Transaksi",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                FormInput(
                    clickable = true,
                    leadingIcon = {
                        Text(text = "Rp")
                    },
                    value = "50.000"
                )
                FormInput(
                    clickable = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = ""
                        )
                    },
                    value = "Makanan & Minuman"
                )
                FormInput(
                    clickable = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = ""
                        )
                    },
                    value = "Jajan Go Food"
                )
                FormInput(
                    clickable = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = ""
                        )
                    },
                    value = "Bank BCA"
                )
                FormInput(
                    clickable = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = ""
                        )
                    },
                    value = "8 April 2023"
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .background(MaterialTheme.colors.surface)
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            ButtonOutlinedPrimary(text = "simpan")
        }

    }
}

@Preview
@Composable
fun PreviewScreenMainEditTransaction() {
    BaseMainApp {
        ScreenMainEditTransaction()
    }
}