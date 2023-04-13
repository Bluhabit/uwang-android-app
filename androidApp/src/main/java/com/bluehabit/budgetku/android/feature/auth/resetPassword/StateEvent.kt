/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.auth.resetPassword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ResetPasswordState(
    val email: String = "",
    val isButtonEnabled : Boolean = false
) : Parcelable

sealed class ResetPasswordEvent {
    object ValidateEmailAddress : ResetPasswordEvent()
    class SendLinkResetPasswordToEmail(var email: String): ResetPasswordEvent()
}