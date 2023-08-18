/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.resetPassword

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import app.trian.mvi.ui.viewModel.MviViewModel
import com.bluehabit.core.ui.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : MviViewModel<ResetPasswordState, ResetPasswordAction>(
    ResetPasswordState()
) {
    init {
        Log.e("HEHEH", savedStateHandle.get<String>(Routes.ResetPassword.argDeeplink).orEmpty())
    }

    override fun onAction(action: ResetPasswordAction) {
        when(action){
            ResetPasswordAction.SubmitCreateNewPassword -> TODO()
            ResetPasswordAction.SubmitRequestResetPassword -> TODO()
        }
    }
}