/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccountSaving.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.core.ui.BaseMainApp
import com.bluehabit.core.ui.components.ItemButtonGroup
import com.bluehabit.core.ui.components.input.FormInput
import com.bluehabit.core.ui.extensions.appendButton
import com.bluehabit.core.ui.theme.Grey100

@Composable
fun ScreenDetailSaving(
    amount: String = "",
    target: String = "",
    onAddAmount: () -> Unit = {},
    onSelectTarget: (String) -> Unit = {},
    onShowBottomSheet:()->Unit={}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 20.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Apa detail dana darurat kamu?",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(20.dp))

        FormInput(
            label = "Jumlah dana darurat yang dipersiapkan",
            placeholder = "0",
            value = amount,
            leadingIcon = {
                Text(
                    text = "Rp"
                )
            },
            onClick = onAddAmount

        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(Grey100)
                .padding(
                    all = 16.dp
                )
        ) {
            ClickableText(
                text = buildAnnotatedString {
                    append(
                        "Belum tahu jumlah dana daruratmu? ",
                    )
                    appendButton(
                        text = "Hitung disini",
                        textColor = MaterialTheme.colors.primary,
                        decoration = TextDecoration.Underline
                    )
                },
                style = MaterialTheme.typography.subtitle2,
                onClick = {
                    onShowBottomSheet()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        FormInput(
            label = "Kapan kamu ingin mencapai ini?",
            placeholder = "0",
            value = target,
            trailingIcon = {
                Text(
                    text = "Bulan"
                )
            },
            onClick = {

            }
        )
        ItemButtonGroup(
            labels = listOf(
                "12",
                "24",
                "48",
                "60"
            ),
            selected = target,
            onClick = {
                onSelectTarget(it)
            }
        )


    }
}

@Preview
@Composable
fun PreviewScreenDetailSaving() {
    BaseMainApp {
        ScreenDetailSaving()
    }
}