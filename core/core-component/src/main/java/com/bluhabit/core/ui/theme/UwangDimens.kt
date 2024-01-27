/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.theme

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import kotlin.math.sqrt

class UwangDimens(
    private val screenWidth: Dp,
    private val screenHeight: Dp,
    private val defaultScreenWidth: Double = 375.0,
    private val defaultScreenHeight: Double = 812.0
) {
    companion object {
        fun from(context: Context): UwangDimens {
            val screenWidth = context
                .resources
                .displayMetrics.widthPixels.dp /
                    context.resources
                        .displayMetrics.density


            val screenHeight = context
                .resources
                .displayMetrics.heightPixels.dp /
                    context.resources
                        .displayMetrics.density
            return UwangDimens(screenWidth, screenHeight)
        }
    }

    val dp_6 = from(6.dp)
    val dp_8 =from(8.dp)
    val dp_10 =from(10.dp)
    val dp_12 =from(12.dp)
    val dp_14 =from(14.dp)
    val dp_16 =from(16.dp)
    val dp_18 =from(18.dp)
    val dp_20 =from(20.dp)
    val dp_22 =from(22.dp)
    val dp_24 =from(24.dp)
    val dp_26 =from(26.dp)
    val dp_28 =from(28.dp)
    val dp_30 =from(30.dp)
    val dp_32 =from(32.dp)
    val dp_34 =from(34.dp)
    val dp_36 =from(36.dp)
    val dp_38 =from(38.dp)

    val sp_6 = from(6.sp)
    val sp_8 =from(8.sp)
    val sp_10 =from(10.sp)
    val sp_12 =from(12.sp)
    val sp_14 =from(14.sp)
    val sp_16 =from(16.sp)
    val sp_18 =from(18.sp)
    val sp_20 =from(20.sp)
    val sp_22 =from(22.sp)
    val sp_24 =from(24.sp)
    val sp_26 =from(26.sp)
    val sp_28 =from(28.sp)
    val sp_30 =from(30.sp)
    val sp_32 =from(32.sp)
    val sp_34 =from(34.sp)
    val sp_36 =from(36.sp)
    val sp_38 =from(38.sp)
    fun from(dimen: Dp): Dp {
        val a = screenHeight.value.toDouble().pow(2.0)
        val b = screenWidth.value.toDouble().pow(2.0)
        val currentDiagonal = sqrt(a + b).dp

        val defWidth = defaultScreenWidth.pow(2)
        val defHeight = defaultScreenHeight.pow(2)
        val defScreenDiagonal = sqrt(defWidth + defHeight)

        val resultCompare = currentDiagonal.value / defScreenDiagonal

        val result = (dimen.value * resultCompare).toInt()

        return result.dp

    }

    fun from(dimen: TextUnit): TextUnit {

        val a = screenHeight.value.toDouble().pow(2.0)
        val b = screenWidth.value.toDouble().pow(2.0)
        val currentDiagonal = sqrt(a + b).dp

        //now design using default size with w = 375 h = 812
        val defWidth = defaultScreenWidth.pow(2)
        val defHeight = defaultScreenHeight.pow(2)
        val defScreenDiagonal = sqrt(defWidth + defHeight)

        val resultCompare = currentDiagonal.value / defScreenDiagonal
        val result = (dimen.value * resultCompare).toInt()

        return result.sp

    }
}
