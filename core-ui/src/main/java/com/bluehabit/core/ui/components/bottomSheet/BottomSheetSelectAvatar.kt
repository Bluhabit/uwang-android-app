/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.components.bottomSheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bluehabit.budgetku.android.base.extensions.gridItems
import com.bluehabit.budgetku.data.remote.dummy.dummyAvatar
import com.bluehabit.core.ui.components.ItemAvatar
import com.bluehabit.core.ui.theme.BudgetKuTheme

@Composable
fun BottomSheetSelectAvatar(
    avatars: List<Int> = listOf(),
    onSelected: () -> Unit = {}
) {
    BaseBottomSheet {
        Text(text = "Pilih Avatar")

        LazyColumn(content = {
            gridItems(
                avatars,
                columnCount = 2
            ) {
                ItemAvatar(
                    avatar = it,
                    selected = it == com.bluehabit.budgetku.data.R.drawable.dummy_avatar_1,
                    onClick = onSelected
                )
            }
        })
    }
}

@Preview
@Composable
fun PreviewBottomSheetSelectAvatar() {
    BudgetKuTheme {
        BottomSheetSelectAvatar(
            avatars = dummyAvatar
        )
    }
}