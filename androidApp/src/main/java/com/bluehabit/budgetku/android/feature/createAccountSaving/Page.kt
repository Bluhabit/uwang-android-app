/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createAccountSaving

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.BottomSheetCalculateEmergencyFund
import com.bluehabit.budgetku.android.components.HeaderStepWithProgress
import com.bluehabit.budgetku.android.components.ScreenInputFeedback
import com.bluehabit.budgetku.android.components.ScreenInputSuccess
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenDetailSaving
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputCategory
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputGoalsName
import com.bluehabit.budgetku.android.feature.createAccountSaving.components.ScreenInputSavingAccount

object CreateAccountSaving {
    const val routeName = "CreateAccountSaving"
}

fun NavGraphBuilder.routeCreateAccountSaving(
    state: ApplicationState,
) {
    composable(CreateAccountSaving.routeName) {
        ScreenCreateAccountSaving(appState = state)
    }
}

@Composable
internal fun ScreenCreateAccountSaving(
    appState: ApplicationState,
) = UIWrapper<CreateAccountSavingViewModel>(appState = appState) {

    val state by uiState.collectAsState()
    with(appState){
        setupBottomSheet {
            BottomSheetCalculateEmergencyFund()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 20.dp
            ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 50.dp
                )

        ) {
            when (state.screenType) {
                ScreenTypeCreateAccountSaving.INPUT_CATEGORY -> ScreenInputCategory()
                ScreenTypeCreateAccountSaving.INPUT_PURPOSE -> ScreenInputGoalsName()
                ScreenTypeCreateAccountSaving.INPUT_ACCOUNT -> ScreenInputSavingAccount()
                ScreenTypeCreateAccountSaving.INPUT_DETAIL -> ScreenDetailSaving()
                ScreenTypeCreateAccountSaving.INPUT_AMOUNT -> ScreenNumPad()
                ScreenTypeCreateAccountSaving.INPUT_RESULT -> ScreenInputSuccess()
                ScreenTypeCreateAccountSaving.INPUT_FEEDBACK -> ScreenInputFeedback()
            }
        }

        if (state.screenType.step in 0..3) {
            HeaderStepWithProgress(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                total = 4,
                progress = 1,
                onClose = {},
                onBackPress = {}
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        horizontal = 20.dp
                    )
            ) {
                ButtonPrimary(
                    text = "Lanjut"
                ) {

                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewScreenCreateAccountSaving() {
    BaseMainApp {
        ScreenCreateAccountSaving(it)
    }
}