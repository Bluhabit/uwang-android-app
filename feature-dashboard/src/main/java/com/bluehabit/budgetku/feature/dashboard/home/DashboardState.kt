/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.dashboard.home

import android.os.Parcelable
import com.bluehabit.budgetku.data.model.ChallengeModel
import com.bluehabit.budgetku.data.model.ExpensesBudgetCategory
import com.bluehabit.budgetku.data.model.LatestTransactionMonthly
import com.bluehabit.budgetku.data.model.UserModel
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.article.ArticleModel
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import com.bluehabit.budgetku.data.model.tutorial.TutorialBudgetModel
import com.bluehabit.budgetku.data.remote.dummy.dummyAccountsHome
import com.bluehabit.budgetku.data.remote.dummy.dummyArticles
import com.bluehabit.budgetku.data.remote.dummy.dummyArticlesCommunity
import com.bluehabit.budgetku.data.remote.dummy.dummyBudgetCategory
import com.bluehabit.budgetku.data.remote.dummy.dummyChallenge
import com.bluehabit.budgetku.data.remote.dummy.dummyLatestMonthlyBudget
import com.bluehabit.budgetku.data.remote.dummy.dummyPosts
import com.bluehabit.budgetku.data.remote.dummy.dummyTransactions
import com.bluehabit.budgetku.data.remote.dummy.dummyTutorial
import com.bluehabit.budgetku.data.remote.dummy.dummyUser
import com.bluehabit.budgetku.feature.dashboard.budget.BottomSheetBudget
import com.bluehabit.core.ui.routes.Routes
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DashboardState(
    val showBalance: Boolean = true,
    val currentScreen:String=Routes.Home.routeName,
    //
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
    val articles: @RawValue List<ArticleModel> = dummyArticles,
    //community
    val selectedTab:Int=0,
    val selectedCategory:Int=0,
    val categories: @RawValue List<String> = listOf(
        "Terbaru",
        "Followed",
        "Semua",
        "Trending",
        "Untukmu"
    ),
    val posts:@RawValue List<PostModel> = dummyPosts,
    val articlesCommunity: @RawValue List<ArticleModel> = dummyArticlesCommunity,
    //budget
    val bottomSheetType: BottomSheetBudget = BottomSheetBudget.FAB,
    val hasBudget: Boolean = true,
    val expensesCategory: @RawValue List<ExpensesBudgetCategory> = dummyBudgetCategory
) : Parcelable

