/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.detailTransaction

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

enum class DetailTransactionBottomSheetType {
    SHARE,
    DELETE_CONFIRMATION
}

@Immutable
@Parcelize
data class DetailTransactionState(
    val bottomSheetType: DetailTransactionBottomSheetType = DetailTransactionBottomSheetType.DELETE_CONFIRMATION
) : Parcelable

@Immutable
@Parcelize
data class DetailTransactionDataState(
    val transactionAmount: BigDecimal = BigDecimal(50_000),
    val transactionName: String = "Jajan Gofood",
    val transactionAccountName: String = "Bank BCA",
    val transactionDate: LocalDate = LocalDate.now(),
    val transactionCategory: String = "Makanan & Minuman",
    val transactionType: String = "Pengeluaran"
) : Parcelable

sealed interface DetailTransactionEvent {
}