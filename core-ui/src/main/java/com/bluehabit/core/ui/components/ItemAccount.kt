/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.extensions.formatToRupiah
import com.bluehabit.budgetku.android.ui.Grey100
import com.bluehabit.budgetku.android.ui.Grey700
import com.bluehabit.budgetku.android.ui.Yellow200
import java.math.BigDecimal

@Composable
fun ItemAccount(
    icon:Int=com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    accountBankName: String = "",
    accountBalance: BigDecimal = BigDecimal.ZERO,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val currentWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val cardWidth = (currentWidth / 2) - 45.dp

    Column(
        modifier = Modifier
            .width(cardWidth)
            .height(cardWidth + 25.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClick()
            }
            .background(
                if (selected) Yellow200
                else Grey100
            )
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(
                    6.dp
                )
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }
        Column {
            Text(
                text = accountBankName,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium,
                color = Grey700
            )
            Text(
                text = accountBalance.formatToRupiah(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview
@Composable
fun PreviewItemAccount() {
    BaseMainApp {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(4) {
                    ItemAccount(
                        accountBalance = BigDecimal(100_000_000),
                        accountBankName = "Bank Jago"
                    )
                }
            }
        )
    }
}