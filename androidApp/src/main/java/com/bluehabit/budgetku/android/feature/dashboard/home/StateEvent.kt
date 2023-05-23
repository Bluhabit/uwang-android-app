/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.ChallengeModel
import com.bluehabit.budgetku.data.model.LatestTransactionMonthly
import com.bluehabit.budgetku.data.model.UserModel
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.article.ArticleModel
import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import com.bluehabit.budgetku.data.model.tutorial.TutorialBudgetModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.data.remote.dummy.dummyArticles
import com.bluehabit.budgetku.data.remote.dummy.dummyChallenge
import com.bluehabit.budgetku.data.remote.dummy.dummyLatestMonthlyBudget
import com.bluehabit.budgetku.data.remote.dummy.dummyTransactions
import com.bluehabit.budgetku.data.remote.dummy.dummyTutorial
import com.bluehabit.budgetku.data.remote.dummy.dummyUser
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
    val profile: @RawValue UserModel = dummyUser,
    val currentMonth: String = "April",
    val remainingBalance: BigDecimal = BigDecimal(3_000_000),
    val expenses: BigDecimal = BigDecimal(7_000_000),
    val income: BigDecimal = BigDecimal(10_000_000),
    //budget
    val remainingBudget: BigDecimal = BigDecimal(5_000_000),
    val usedAmount: BigDecimal = BigDecimal(7_000_000),
    val totalBudget: BigDecimal = BigDecimal(10_000_000),
    val score: Int = 40,
    val latestTransaction: @RawValue List<LatestTransactionMonthly> = dummyLatestMonthlyBudget,
    val accounts: @RawValue List<AccountModel> = dummyAccountsHome,
    val transactions: @RawValue List<TransactionModel> = dummyTransactions,
    val challenge: @RawValue List<ChallengeModel> = dummyChallenge,
    val tutorial: @RawValue List<TutorialBudgetModel> = dummyTutorial,
    val articles: @RawValue List<ArticleModel> = dummyArticles
) : Parcelable

sealed class HomeEvent {
    class SetName(var name: String) : HomeEvent()
}