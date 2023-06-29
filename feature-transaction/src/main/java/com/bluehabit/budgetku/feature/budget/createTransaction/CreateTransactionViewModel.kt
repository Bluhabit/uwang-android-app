/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createTransaction

import app.trian.mvi.ui.extensions.formatDecimal
import app.trian.mvi.ui.viewModel.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(
) : MviViewModel<CreateTransactionState, CreateTransactionIntent, CreateTransactionAction>(
    CreateTransactionState()
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


    override fun onAction(action: CreateTransactionAction) {
        when (action) {
            CreateTransactionAction.NexPage -> calculatePage(true)
            CreateTransactionAction.PrevPage -> calculatePage(false)
            is CreateTransactionAction.ChangeBottomSheet -> commit {
                copy(
                    bottomSheetType = action.bottomSheetType
                )
            }

            CreateTransactionAction.ClearNominal -> clearNominal()
            is CreateTransactionAction.Input -> appendNominal(action.nominal)
            CreateTransactionAction.RemoveNominal -> removeNominal()
            CreateTransactionAction.AddMoreTransaction -> async {
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