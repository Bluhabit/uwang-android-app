/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.listAccount

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.account.AccountSavingModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.data.remote.dummy.dummyAcountSaving
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListAccountState(
    val selectedTab: Int = 0
) : Parcelable

@Immutable
@Parcelize
data class ListAccountDataState(
    val balance: BigDecimal = BigDecimal(6_000_000),
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome,
    val savingAccounts: @RawValue List<AccountSavingModel> = dummyAcountSaving,
) : Parcelable

sealed interface ListAccountEvent {

}