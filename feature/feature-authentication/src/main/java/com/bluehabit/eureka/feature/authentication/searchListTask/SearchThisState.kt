/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.feature.authentication.searchListTask

import android.os.Parcelable
import app.trian.mvi.ui.extensions.Empty
import app.trian.mvi.ui.internal.contract.MviState
import com.bluehabit.eureka.data.authentication.AuthConstant
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SearchThisState(
    val currentScreen: Int = AuthConstant.SEARCH_LIST_TASK,
    val word: String = String.Empty,

    val isLoading:Boolean=false,
    override val effect: @RawValue SearchThisEffect = SearchThisEffect.Nothing
) : MviState<SearchThisEffect>(), Parcelable