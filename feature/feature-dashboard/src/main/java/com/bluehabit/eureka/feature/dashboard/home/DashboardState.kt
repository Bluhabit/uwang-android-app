/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.dashboard.home

import android.os.Parcelable
import com.bluehabit.eureka.feature.dashboard.home.components.BottomSheetBudget
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
    val currentMonth: String = "April",
    val remainingBalance: BigDecimal = BigDecimal(3_000_000),
    val expenses: BigDecimal = BigDecimal(7_000_000),
    val income: BigDecimal = BigDecimal(10_000_000),
    //budget
    val remainingBudget: BigDecimal = BigDecimal(5_000_000),
    val usedAmount: BigDecimal = BigDecimal(7_000_000),
    val totalBudget: BigDecimal = BigDecimal(10_000_000),
    val score: Int = 40,

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
    //budget
    val bottomSheetType: BottomSheetBudget = BottomSheetBudget.FAB,
    val hasBudget: Boolean = true,
) : Parcelable

