/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.createTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.feature.transaction.createTransaction.components.CreateTransactionBottomSheetType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDate
import javax.annotation.concurrent.Immutable


@Immutable
@Parcelize
data class CreateTransactionState(
    val step: Int = 1,
    val bottomSheetType: CreateTransactionBottomSheetType = CreateTransactionBottomSheetType.CATEGORY,
    val nominal:String="",
    val tempNominal:String="",
    val isExpenses:Boolean=true,
    val selectedAccount:String="",
    val transactionName:String="",
    val selectedCategory:String="",
    val transactionDate:LocalDate?=null,

    val transactionCategory:String="",
    //feedback
    val feedback:String="",

    //data
    val accounts:@RawValue List<AccountModel> = dummyAccountsHome
) : Parcelable

