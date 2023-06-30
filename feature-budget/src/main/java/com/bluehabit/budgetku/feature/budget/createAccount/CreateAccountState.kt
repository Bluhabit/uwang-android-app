/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.createAccount

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.CategoryFinancialAccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyFinancialAccount
import com.bluehabit.budgetku.feature.budget.createAccount.components.ScreenType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreateAccountState(
    val screenType: ScreenType = ScreenType.MAIN,
    val nominal:String="0",
    val tempNominal:String="",

    val selectedAccountName:String="Bank Jago",
    val selectedAccountIcon:Int=com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    //data
    val financialAccount: @RawValue List<CategoryFinancialAccountModel>  = dummyFinancialAccount
) : Parcelable