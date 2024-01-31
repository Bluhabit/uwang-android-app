/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.presentation.authentication.completeProfile

import android.graphics.Bitmap
import com.bluehabit.core.ui.R
import com.bluhabit.blu.android.presentation.authentication.completeProfile.screen.PreferenceItem
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
data class CompleteProfileState(

    //global
    val currentScreen: Int = 0,
    //
    val usernameState:String="",
    val usernameError:Boolean=false,
    val usernameErrorText:String="",

    val avatar:Bitmap? = null,

    // Input Dob Screen
    val dateOfBirthState: LocalDate? = null,
    val dateOfBirthError: Boolean = false,
    val dateOfBirthErrorText: String = "",
    // Set Preference Screen
    val preferenceItems: List<PreferenceItem> = listOf(
        PreferenceItem(false, "Penganggaran", R.drawable.onboard_1),
        PreferenceItem(false, "Tabungan", R.drawable.preference_image_2),
        PreferenceItem(false, "Investasi", R.drawable.preference_image_3),
        PreferenceItem(false, "Pembayaran Tagihan", R.drawable.preference_image_4),
        PreferenceItem(false, "Hutang / Kredit", R.drawable.preference_image_5),
        PreferenceItem(false, "Analisis Pemasukan", R.drawable.preference_image_5),
        PreferenceItem(false, "Budgeting", R.drawable.preference_image_2),
    ),
    val showDialogSuccess: Boolean = false
)