/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.BottomSheetAccount
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetDatePicker
import com.bluehabit.budgetku.android.feature.editTransaction.components.ScreenMainEditTransaction

object EditTransaction {
    const val routeName = "EditTransaction"
}

fun NavGraphBuilder.routeEditTransaction(
    state: ApplicationState,
) {
    composable(EditTransaction.routeName) {
        ScreenEditTransaction(appState = state)
    }
}

@Composable
internal fun ScreenEditTransaction(
    appState: ApplicationState,
) = UIWrapper<EditTransactionViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    with(appState) {
        setupBottomSheet {
            when (state.bottomSheetType) {
                BottomSheetTypeEditTransaction.DATE -> BottomSheetDatePicker()
                BottomSheetTypeEditTransaction.CATEGORY -> {}
                BottomSheetTypeEditTransaction.ACCOUNT -> BottomSheetAccount()
            }
        }
    }
    when (state.screenType) {
        ScreenType.NUMPAD -> ScreenNumPad()
        ScreenType.MAIN -> ScreenMainEditTransaction()
    }

}

@Preview
@Composable
fun PreviewScreenEditTransaction() {
    BaseMainApp {
        ScreenEditTransaction(it)
    }
}