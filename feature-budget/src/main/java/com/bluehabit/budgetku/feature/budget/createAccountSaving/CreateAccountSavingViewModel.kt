/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccountSaving

import app.trian.mvi.ui.extensions.formatDecimal
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountSavingViewModel @Inject constructor(
) : MviViewModel<CreateAccountSavingState, CreateAccountSavingIntent, CreateAccountSavingAction>(
    CreateAccountSavingState()
) {

    private fun appendNominal(nominal: String) = asyncWithState {
        if (nominal == "0" || nominal == "000") {
            if (tempNominal.isEmpty()) return@asyncWithState
        }
        var currentNominal = tempNominal
        currentNominal += nominal
        commit {
            copy(
                tempNominal = currentNominal,
                nominal = currentNominal.toBigDecimal().formatDecimal()
            )
        }
    }

    private fun removeNominal() = asyncWithState {
        if (tempNominal.isNotEmpty()) {
            var currentNominal = if (tempNominal.length == 1) "" else tempNominal.dropLast(1)

            commit {
                copy(
                    tempNominal = currentNominal,
                    nominal = if (currentNominal.isEmpty()) "" else currentNominal.toBigDecimal().formatDecimal()
                )
            }
        }
    }

    private fun clearNominal() = asyncWithState {
        var currentNominal = ""
        commit {
            copy(
                tempNominal = currentNominal,
                nominal = currentNominal
            )
        }
    }

    private fun onNext() = asyncWithState {
        if (screenType < 5) {
            commit {
                copy(
                    screenType = screenType + 1
                )
            }
        }
    }

    private fun onPrev() = asyncWithState {
        if (screenType > 1) {
            commit {
                copy(
                    screenType = screenType - 1
                )
            }
        }
    }

    override fun onAction(action: CreateAccountSavingAction) {
        when (action) {
            CreateAccountSavingAction.Next -> onNext()
            CreateAccountSavingAction.Prev -> onPrev()
            CreateAccountSavingAction.Clear -> clearNominal()
            is CreateAccountSavingAction.Input -> appendNominal(action.nominal)
            CreateAccountSavingAction.Remove -> removeNominal()
        }
    }

}