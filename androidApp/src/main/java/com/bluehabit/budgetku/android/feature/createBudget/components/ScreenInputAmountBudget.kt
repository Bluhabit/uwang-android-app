/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.AnnotationTextItem
import com.bluehabit.budgetku.android.components.ButtonPrimary
import com.bluehabit.budgetku.android.components.FormInput
import com.bluehabit.budgetku.android.components.TextWithAction
import com.bluehabit.budgetku.android.ui.Yellow600

@Composable
fun ScreenInputAmountBudget(
    amount: String = "",
    onInputAmount: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hero_create_budget),
                    contentDescription = "",
                    modifier = Modifier.size(240.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        all = 16.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.text_title_card_input_amount),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                FormInput(
                    value = amount,
                    label = stringResource(R.string.label_input_amount_per_month_create_budget),
                    placeholder = stringResource(R.string.label_input_amount_create_budget),
                    leadingIcon = {
                        Text(
                            text = stringResource(R.string.prefix_rupiah),
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    clickable = true,
                    onClick = onInputAmount
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_idea_star),
                        contentDescription = "",
                        tint = Yellow600
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.text_message_tips_savings_create_budget),
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                )
                .background(MaterialTheme.colors.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_idea_bulb),
                    contentDescription = ""
                )
                TextWithAction(
                    labels = listOf(
                        AnnotationTextItem.Text(stringResource(R.string.text_benefit_budget_first)),
                        AnnotationTextItem.Button(stringResource(R.string.text_button_benefit_budget_first), true)
                    ),
                    onTextClick = {}
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
                ButtonPrimary(
                    text = stringResource(R.string.text_button_save_amount_create_budget),
                    onClick = onSubmit
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreenInputAmountBudget() {
    BaseMainApp {
        ScreenInputAmountBudget()
    }
}