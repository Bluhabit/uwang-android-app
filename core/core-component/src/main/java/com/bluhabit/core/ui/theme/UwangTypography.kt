/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object UwangTypography {
    object DisplayXXL {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 64.sp,
            lineHeight = 76.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object DisplayXL {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 56.sp,
            lineHeight = 64.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object DisplayLarge {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 48.sp,
            lineHeight = 56.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object DisplayMedium {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp,
            lineHeight = 44.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object DisplaySmall {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            lineHeight = 36.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object DisplayXS {
        val Medium = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 30.sp
        )
        val SemiBold = Medium.copy(fontWeight = FontWeight.SemiBold)
        val Bold = Medium.copy(fontWeight = FontWeight.Bold)
    }

    object BodyXL {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 26.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object BodyLarge {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object BodyMedium {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object BodySmall {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object BodyXS {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object LabelLarge {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 18.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object LabelMedium {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }

    object LabelSmall {
        val Regular = TextStyle(
            fontFamily = FontFamilyInter,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 12.sp
        )
        val Medium = Regular.copy(fontWeight = FontWeight.Medium)
        val SemiBold = Regular.copy(fontWeight = FontWeight.SemiBold)
    }
}