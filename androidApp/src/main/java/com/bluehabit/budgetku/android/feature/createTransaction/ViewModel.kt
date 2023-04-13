/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createTransaction

import com.bluehabit.budgetku.android.base.BaseViewModelData
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

        val percentage = when (page) {
            1 -> 0.15f
            2 -> 0.35f
            3 -> 0.45f
            4 -> 0.6f
            5 -> 0.7f
            6 -> 0.8f
            7 -> 1f
            else -> 0f
        }

        commit {
            copy(
                step = page,
                percentage = percentage
            )
        }

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
        }
    }

}