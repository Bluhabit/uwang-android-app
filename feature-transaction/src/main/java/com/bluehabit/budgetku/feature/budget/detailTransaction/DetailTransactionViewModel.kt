/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.detailTransaction

import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.budgetku.data.remote.dummy.dummyListTransaction
import com.bluehabit.core.ui.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTransactionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : MviViewModel<DetailTransactionState, DetailTransactionIntent, DetailTransactionAction>(
    DetailTransactionState()
) {

    private fun getId() = savedStateHandle.get<String>(Routes.DetailTransaction.argKey).orEmpty()

    private fun getDetailTransaction() = async {
        dummyListTransaction.find {
            it.transactionId == getId()
        }?.let {
            commit {
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


    override fun onAction(action: DetailTransactionAction) {
        when (action) {
            DetailTransactionAction.GetDetailTransaction -> getDetailTransaction()
        }
    }

}