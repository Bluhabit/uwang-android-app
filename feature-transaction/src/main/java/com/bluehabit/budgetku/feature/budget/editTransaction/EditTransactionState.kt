/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.budget.editTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.feature.budget.editTransaction.components.BottomSheetTypeEditTransaction
import com.bluehabit.budgetku.feature.budget.editTransaction.components.ScreenType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class EditTransactionState(
    val bottomSheetType: BottomSheetTypeEditTransaction = BottomSheetTypeEditTransaction.ACCOUNT,
    val screenType: ScreenType = ScreenType.MAIN,

    val isExpenses: Boolean = true,
    val categoryName: String = "Makanan & Minuman",
    val categoryIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_category_makan_minum,

    val transactionDate: LocalDate? = LocalDate.now(),

    val accountName: String = "Bank jago",
    val accountIcon: Int = com.bluehabit.budgetku.data.R.drawable.dummy_bank_jago,
    val accountId:String="",

    val nominal: String = "1,000,000",
    val tempNominal: String = "1000000",
    //data
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome
) : Parcelable
