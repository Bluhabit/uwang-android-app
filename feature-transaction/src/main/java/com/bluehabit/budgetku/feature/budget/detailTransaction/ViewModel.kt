/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.detailTransaction

import androidx.lifecycle.SavedStateHandle
import com.bluehabit.budgetku.data.remote.dummy.dummyListTransaction
import com.bluehabit.core.ui.routes.Routes
import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTransactionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModelData<DetailTransactionState, DetailTransactionDataState, DetailTransactionEvent>(
    DetailTransactionState(),
    DetailTransactionDataState()
) {
    init {
        handleActions()
    }

    fun getId() = savedStateHandle.get<String>(Routes.DetailTransaction.argKey).orEmpty()

    private fun getDetailTransaction() = async {
        dummyListTransaction.find {
            it.transactionId == getId()
        }?.let {
            commitData {
                copy(
                    transactionCategory = it.transactionCategory,
                    transactionDate = it.transactionDate,
                    transactionName = it.transactionName,
                    transactionAccountName = it.transactionAccountName,
                    transactionAmount = it.transactionAmount,
                    transactionType = if (it.isTransactionExpenses) "Pengeluaran" else "Pemasukkan",
                    transactionIcon = it.transactionIcon,
                    transactionAccountIcon = it.transactionIcon,

                    isLoading = false
                )
            }
        }
    }

    override fun handleActions() = onEvent { event ->
        when (event) {
            DetailTransactionEvent.GetDetailTransaction -> getDetailTransaction()
        }
    }

}