/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.base.BaseMainApp
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.ScreenNumPad
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetConfirmation
import com.bluehabit.budgetku.android.feature.createBudget.components.ScreenInputAmountBudget
import com.bluehabit.budgetku.android.feature.resultCreateBudget.ResultCreateBudget

object CreateBudget {
    const val routeName = "CreateBudget"
}

fun NavGraphBuilder.routeCreateBudget(
    state: ApplicationState,
) {
    composable(CreateBudget.routeName) {
        ScreenCreateBudget(appState = state)
    }
}

@Composable
internal fun ScreenCreateBudget(
    appState: ApplicationState,
) = UIWrapper<CreateBudgetViewModel>(appState = appState) {
    val state by uiState.collectAsState()

    with(appState) {
        setupTopAppBar {
            TopAppbarCreateBudget {
                navigateUp()
            }
        }
        setupBottomSheet {
            BottomSheetConfirmation(
                title = "Yakin membatalkan perubahan?",
                message = "Data yang sudah kamu ubah belum tersimpan dan akan hilang.",
                textConfirmation = "Yakin",
                textCancel = "Batal",
                onDismiss = {
                    hideBottomSheet()
                },
                onConfirm = {
                    hideBottomSheet()
                    navigateUp()
                }
            )
        }
    }

    fun onBackPressed() {
        if (state.step == 1) {
            showBottomSheet()
        }
        if (state.step > 1) {
            dispatch(CreateBudgetEvent.Prev)
        }
    }
    BackHandler {
        onBackPressed()
    }

    when (state.step) {
        1 -> {
            appState.showTopAppBar()
            ScreenInputAmountBudget(
                amount = state.nominal,
                onInputAmount = {
                    dispatch(CreateBudgetEvent.Next)
                },
                onSubmit = {
                    navigateAndReplaceAll(ResultCreateBudget.routeName)
                }
            )
        }

        2 -> {
            appState.hideTopAppBar()
            ScreenNumPad(
                value = state.nominal,
                onChange = {
                    dispatch(CreateBudgetEvent.Input(it))
                },
                onSubmit = {
                    dispatch(CreateBudgetEvent.Prev)

                },
                onClear = {
                    dispatch(CreateBudgetEvent.Clear)
                },
                onRemove = {
                    dispatch(CreateBudgetEvent.Remove)
                },
                onDismiss = {
                    dispatch(CreateBudgetEvent.Prev)
                }
            )
        }
    }

}

@Composable
fun TopAppbarCreateBudget(
    onBackPressed: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackPressed) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                tint = MaterialTheme.colors.surface
            )
        }
        Text(
            text = "Atur Total Budget",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.surface
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}


@Preview
@Composable
fun PreviewScreenCreateBudget() {
    BaseMainApp(
        topAppBar = {
            TopAppbarCreateBudget()
        }
    ) {
        ScreenCreateBudget(it)
    }
}