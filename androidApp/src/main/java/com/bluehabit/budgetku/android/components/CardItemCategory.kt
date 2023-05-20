/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.ui.Grey200
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey900

@Composable
fun CardItemCategory(
    icon: Int = com.bluehabit.budgetku.data.R.drawable.ic_dummy_saving,
    categoryName: String = "",
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(
                enabled = true,
                onClick = onClick
            )
            .background(
                if (selected) MaterialTheme.colors.primary else Grey200
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.small,
                color = Grey300
            )
            .padding(
                all = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = categoryName,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium,
            color = Grey900
        )

    }
}

@Preview
@Composable
fun PreviewCardItemCategory() {
    BaseMainApp {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                horizontal = 20.dp
            ),
            content = {
                items(12) {
                    CardItemCategory()
                }
            }
        )
    }
}