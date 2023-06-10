/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createTransaction.components

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.R
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.theme.Yellow200
import com.bluehabit.core.ui.theme.Yellow800

@Composable
fun ScreenInputTransactionType(
    selected: String = "",
    onSelectedType: (isExpenses: Boolean) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
            .padding(
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_input_transaction_type),
            contentDescription = "",
            modifier = Modifier.size(260.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.text_title_input_transaction_type_create_transaction),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.9f),
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonTransactionType(
            name = stringResource(R.string.text_transaction_type_income),
            selected = selected == stringResource(R.string.text_transaction_type_income),
            icon = R.drawable.arrow_long_circle_up,
            iconColor = Color(0xFF57C45C),
            onClick = {
                onSelectedType(false)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonTransactionType(
            name = stringResource(R.string.text_transaction_type_expenses),
            selected = selected == stringResource(R.string.text_transaction_type_expenses),
            icon = R.drawable.arrow_long_circle_down,
            iconColor = Color(0xFFFE3419),
            onClick = {
                onSelectedType(true)
            }
        )

    }
}

@Composable
fun ButtonTransactionType(
    icon: Int = 0,
    name: String = "",
    iconColor: Color = Color.Red,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(
                MaterialTheme.shapes.small
            )
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .background(
                if (selected) Yellow200
                else MaterialTheme.colors.surface
            )
            .padding(
                horizontal = 20.dp
            ),
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Medium
            )
        }
        if (selected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check_circle),
                contentDescription = "",
                tint = Yellow800,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
            )
        }

    }
}

@Preview
@Composable
fun PreviewScreenInputTransactionType() {
    BaseMainApp {
        ScreenInputTransactionType()
    }
}