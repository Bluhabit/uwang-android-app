/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.profile

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.UserModel
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.data.remote.dummy.dummyUser
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ProfileState(
    val a: String = ""
) : Parcelable

@Immutable
@Parcelize
data class ProfileDataState(
    val detailUser: @RawValue UserModel = dummyUser,
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome
) : Parcelable

sealed interface ProfileEvent {
}