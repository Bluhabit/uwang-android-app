/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction

import com.bluehabit.budgetku.android.base.BaseViewModelData
import com.bluehabit.budgetku.android.base.extensions.formatDecimal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(
) : BaseViewModelData<CreateTransactionState, CreateTransactionDataState, CreateTransactionEvent>(
    CreateTransactionState(),
    CreateTransactionDataState()
) {
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

    override fun handleActions() = onEvent {
        when (it) {
            CreateTransactionEvent.NexPage -> calculatePage(true)
            CreateTransactionEvent.PrevPage -> calculatePage(false)
            is CreateTransactionEvent.ChangeBottomSheet -> commit {
                copy(
                    bottomSheetType = it.bottomSheetType
                )
            }

            CreateTransactionEvent.ClearNominal -> clearNominal()
            is CreateTransactionEvent.Input -> appendNominal(it.nominal)
            CreateTransactionEvent.RemoveNominal -> removeNominal()
            CreateTransactionEvent.AddMoreTransaction -> async {
                commit {
                    copy(
                        step = 1,
                        transactionDate = null,
                        selectedAccount = "",
                        selectedCategory = "",
                        isExpenses = true,
                        tempNominal = "",
                        nominal = "",
                        transactionName = ""
                    )
                }
            }
        }
    }

}