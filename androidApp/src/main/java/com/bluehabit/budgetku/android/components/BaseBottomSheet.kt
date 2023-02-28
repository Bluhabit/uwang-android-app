/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.components

import androidx.compose.runtime.Composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.listener.BottomSheetType

@Composable
fun BaseBottomSheet(
    state: ApplicationState
) {
    with(state) {
        when (bottomSheetType) {
            BottomSheetType.BASIC -> TODO()
            BottomSheetType.DASHBOARD -> TODO()
        }
    }
}