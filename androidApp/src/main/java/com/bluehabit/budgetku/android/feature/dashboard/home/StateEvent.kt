/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import android.os.Parcelable
import com.bluehabit.budgetku.android.components.LatestTransactionMonthly
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class HomeState(
    val showBalance: Boolean = true
) : Parcelable

@Immutable
@Parcelize
data class HomeDataState(
    val displayName: String = "Mario Jr.",
    val currentMonth: String = "April",
    val remainingBalance: BigDecimal = BigDecimal(20_000_000),
    val expenses: BigDecimal = BigDecimal(30_000_000),
    val income: BigDecimal = BigDecimal(100_000_000),
    //budget
    val remainingBudget: BigDecimal = BigDecimal(2_000_000),
    val usedAmount: BigDecimal = BigDecimal(3_000_000),
    val totalBudget: BigDecimal = BigDecimal(4_000_000),
    val score: Int = 50,
    val transactions: @RawValue List<LatestTransactionMonthly> = listOf(
        LatestTransactionMonthly(
            transactionName = "Makanan",
            usedAmount = BigDecimal(80_000)
        ),
        LatestTransactionMonthly(
            transactionName = "Kendaraan",
            usedAmount = BigDecimal(100_000)
        )
    )
) : Parcelable

sealed class HomeEvent {
    class SetName(var name: String) : HomeEvent()
}