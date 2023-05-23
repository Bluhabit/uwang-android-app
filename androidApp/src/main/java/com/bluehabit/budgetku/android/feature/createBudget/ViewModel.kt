/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createBudget

import com.bluehabit.budgetku.android.base.BaseViewModelData
import com.bluehabit.budgetku.android.base.extensions.formatDecimal
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.trimSubstring
import java.math.BigDecimal
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


    override fun handleActions() = onEvent { event ->
        when (event) {
            is CreateBudgetEvent.Input -> appendNominal(event.nominal)
            CreateBudgetEvent.Clear -> clearNominal()
            CreateBudgetEvent.Remove -> removeNominal()
        }
    }

}