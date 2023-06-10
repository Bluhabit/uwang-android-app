/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createBudget

import com.bluehabit.core.ui.extensions.formatDecimal
import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateBudgetViewModel @Inject constructor(
) : BaseViewModelData<CreateBudgetState, CreateBudgetDataState, CreateBudgetEvent>(CreateBudgetState(), CreateBudgetDataState()) {
    init {
        handleActions()
    }

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

    private fun calculatePage(isNext: Boolean) = asyncWithState {
        val page = if (isNext) {
            if (step < 7) {
                step + 1
            } else step
        } else {
            if (step > 1) {
                step - 1
            } else step
        }

        commit { copy(step = page) }

    }

    override fun handleActions() = onEvent { event ->
        when (event) {
            is CreateBudgetEvent.Input -> appendNominal(event.nominal)
            CreateBudgetEvent.Clear -> clearNominal()
            CreateBudgetEvent.Remove -> removeNominal()
            CreateBudgetEvent.Next -> calculatePage(true)
            CreateBudgetEvent.Prev -> calculatePage(false)
        }
    }

}