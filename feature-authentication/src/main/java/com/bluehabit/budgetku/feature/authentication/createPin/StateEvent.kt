/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.feature.authentication.createPin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CreatePinState(var a : String = "") : Parcelable

sealed class CreatePinEvent{}