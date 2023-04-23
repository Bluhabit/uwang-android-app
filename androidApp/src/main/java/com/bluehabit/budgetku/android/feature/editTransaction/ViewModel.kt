/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.editTransaction

import com.bluehabit.budgetku.android.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditTransactionViewModel @Inject constructor(

) : BaseViewModel<EditTransactionState, EditTransactionEvent>(EditTransactionState()) {

    init {
        handleActions()
    }

    override fun handleActions() = onEvent {
        when(it) {
            is EditTransactionEvent.EditAccount -> {
                hideBottomSheet()
                commit { copy(bottomSheetType = EditTransactionBottomSheetType.ACCOUNT) }
                showBottomSheet()
            }
            is EditTransactionEvent.EditCategory -> {
                hideBottomSheet()
                commit { copy(bottomSheetType = EditTransactionBottomSheetType.CATEGORY) }
                showBottomSheet()
            }
            is EditTransactionEvent.EditDate -> {
                hideBottomSheet()
                commit { copy(bottomSheetType = EditTransactionBottomSheetType.DATE) }
                showBottomSheet()
            }
            is EditTransactionEvent.EditNote -> {
                commit { copy(bottomSheetType = EditTransactionBottomSheetType.NOTE) }
            }
            is EditTransactionEvent.EditValue -> {
                hideBottomSheet()
                commit { copy(bottomSheetType = EditTransactionBottomSheetType.VALUE) }
                showBottomSheet()
            }
            is EditTransactionEvent.SaveChanges -> {

            }
        }
    }

}