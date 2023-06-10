/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.report

import com.bluehabit.core.ui.viewModel.BaseViewModel
import com.bluehabit.core.ui.viewModel.BaseViewModelData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
) : BaseViewModelData<ReportState,ReportsDataState, ReportEvent>(ReportState(),ReportsDataState()) {
    init {
        handleActions()
    }

    override fun handleActions() = onEvent {}

}