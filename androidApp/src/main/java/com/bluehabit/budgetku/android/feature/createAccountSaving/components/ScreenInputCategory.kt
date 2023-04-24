/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.components.CardItemCategory
import com.bluehabit.budgetku.data.model.category.SavingCategory

@Composable
fun ScreenInputCategory(
    selected: Int = 0,
    categories: List<SavingCategory> = listOf(),
    onSelected: (Int) -> Unit = {}
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Apakah kategori goal finansial kamu saat ini?",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "*wajib kamu isi",
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colors.error
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                itemsIndexed(categories) { index, item ->
                    CardItemCategory(
                        icon = item.icon,
                        categoryName = item.categoryName,
                        selected = selected == index,
                        onClick = {
                            onSelected(index)
                        }
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewScreenInputCategory() {
    BaseMainApp {
        ScreenInputCategory()
    }
}