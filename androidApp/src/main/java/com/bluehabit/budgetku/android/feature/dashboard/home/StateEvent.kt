/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.home

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.components.ChallengeModel
import com.bluehabit.budgetku.android.components.LatestTransactionMonthly
import com.bluehabit.budgetku.android.ui.Grey900
import com.bluehabit.budgetku.android.ui.Pink500
import com.bluehabit.budgetku.android.ui.Yellow500
import com.bluehabit.budgetku.data.model.account.AccountModel
import com.bluehabit.budgetku.data.model.article.ArticleModel
import com.bluehabit.budgetku.data.model.transaction.TransactionModel
import com.bluehabit.budgetku.data.model.tutorial.TutorialBudgetModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
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
    val latestTransaction: @RawValue List<LatestTransactionMonthly> = listOf(
        LatestTransactionMonthly(
            transactionName = "Makanan",
            usedAmount = BigDecimal(80_000)
        ),
        LatestTransactionMonthly(
            transactionName = "Kendaraan",
            usedAmount = BigDecimal(100_000)
        )
    ),
    val accounts: @RawValue List<AccountModel> = listOf(
        AccountModel(
            icon = R.drawable.ic_jago,
            accountName = "Bank Jago",
            accountBalance = BigDecimal(1_000_000)
        ),
        AccountModel(
            icon = R.drawable.ic_bca,
            accountName = "Bank BCA",
            accountBalance = BigDecimal(10_000_000)
        ),
        AccountModel(
            icon = R.drawable.ic_jago,
            accountName = "Bank Jago",
            accountBalance = BigDecimal(500_000)
        ),
    ),
    val transactions: @RawValue List<TransactionModel> = listOf(
        TransactionModel(
            transactionName = "McDonald",
            isTransactionExpenses = false,
            transactionAmount = BigDecimal(90_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank BCA",
            transactionCategory = "Makanan"
        ),
        TransactionModel(
            transactionName = "Listrik",
            isTransactionExpenses = true,
            transactionAmount = BigDecimal(100_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank Jago",
            transactionCategory = "Utilitas"
        ),
        TransactionModel(
            transactionName = "KFC",
            isTransactionExpenses = false,
            transactionAmount = BigDecimal(10_000),
            transactionDate = LocalDate.now(),
            transactionAccountName = "Bank Jago",
            transactionCategory = "Makanan"
        )
    ),
    val challenge: @RawValue List<ChallengeModel> = listOf(
        ChallengeModel(
            title = "Follow the money trail",
            message = "Answer questions on budgeting and savings",
            progress = 0.4f,
            totalPoints = 1823,
            targetPoints = 2000,
            color = Pink500,
            textColor= Color.White,
            image = R.drawable.ic_dummy_challenge,
        ),
        ChallengeModel(
            title = "Investore Extraoordinaire",
            message = "Explore questions on investment Options",
            progress = 0.5f,
            totalPoints = 1923,
            targetPoints = 2000,
            color = Yellow500,
            textColor= Grey900,
            image = R.drawable.ic_dummy_challenge_2,
        )
    ),
    val tutorial: @RawValue List<TutorialBudgetModel> = listOf(
        TutorialBudgetModel(
            title = "Cara Transaksi di Budgetku",
            image = R.drawable.ic_dummy_tutorial
        ),
        TutorialBudgetModel(
            title = "Cara Atur Anggran",
            image = R.drawable.ic_dummy_tutorial_2
        ),
        TutorialBudgetModel(
            title = "Cara Buat Tujuan",
            image = R.drawable.ic_dummy_tutorial_3
        )
    ),
    val articles: @RawValue List<ArticleModel> = listOf(
        ArticleModel(
            title = "Pusat Bantuan",
            body = "Punya kendala atau pertanyyan terkait Budgetku? kamu bisa kirim di sini",
            image = R.drawable.ic_dummy_article,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Cerdas Finansial",
            body = "Yuk melek finansial bersama Budgetku. Tersedia course keuangan untukmu",
            image = R.drawable.ic_dummy_article_2,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Promo & Hadiah",
            body = "Yuk cek berbagai promo menarik di aplikasi Budgetku Sekarang!",
            image = R.drawable.ic_dummy_article_3,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Tips Keuangan",
            body = "Bingung ngatur budget? disini kamu bisa tahu tips ngatur keuangan",
            image = R.drawable.ic_dummy_article_4,
            date = LocalDateTime.now(),
            likes = 200
        )
    )
) : Parcelable

sealed class HomeEvent {
    class SetName(var name: String) : HomeEvent()
}