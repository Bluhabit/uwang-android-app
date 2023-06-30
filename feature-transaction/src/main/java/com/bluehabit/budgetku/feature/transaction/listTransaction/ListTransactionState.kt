/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.transaction.listTransaction

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import com.bluehabit.budgetku.data.remote.dummy.dummyListTransaction
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ListTransactionState(
    val a: String = "",
    val balance: BigDecimal = BigDecimal(3_000_000),
    val income:BigDecimal = BigDecimal(10_000_000),
    val expenses:BigDecimal = BigDecimal(7_000_000),
    val currentExpense:BigDecimal = BigDecimal(324_000),
    val transactions: @RawValue List<TransactionModel> = dummyListTransaction,
) : Parcelable
