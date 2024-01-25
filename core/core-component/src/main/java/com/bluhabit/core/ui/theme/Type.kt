/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.core.ui.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bluehabit.core.ui.R


val FontFamilyInter = FontFamily(
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.inter_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.inter_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.inter_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    ),
    h6 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = FontFamilyInter,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    ),
)

val FontFamilyPlusJakartaSans = FontFamily(
    Font(
        resId = R.font.plus_jakarta_sans_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.plus_jakarta_sans_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.plus_jakarta_sans_semi_bold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.plus_jakarta_sans_semi_bold_italic,
        weight = FontWeight.SemiBold,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.plus_jakarta_sans_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.plus_jakarta_sans_medium_italic,
        weight = FontWeight.Medium,
        style = FontStyle.Italic
    ),
    Font(
        resId = R.font.plus_jakarta_sans_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    ),
)

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

object CustomTypography {
    object Display {
        object XXL {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp,
                lineHeight = 82.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 64.sp,
                lineHeight = 82.sp,
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 64.sp,
                lineHeight = 82.sp
            )
        }

        object XL {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 56.sp,
                lineHeight = 72.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 56.sp,
                lineHeight = 72.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 56.sp,
                lineHeight = 72.sp
            )
        }

        object Large {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                lineHeight = 61.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 48.sp,
                lineHeight = 61.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 48.sp,
                lineHeight = 60.sp
            )
        }

        object Medium {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                lineHeight = 46.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                lineHeight = 46.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 36.sp,
                lineHeight = 46.sp
            )
        }

        object Small {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 39.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                lineHeight = 37.8.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                lineHeight = 36.sp
            )
        }

        object XS {
            val W700 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 31.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                lineHeight = 37.8.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                lineHeight = 36.sp
            )
        }
    }

    object Body {
        object XL {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 26.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 26.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 26.sp
            )
        }

        object Large {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 22.68.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        }

        object Medium {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }

        object Small {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }

        object XS {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
        }
    }

    object Label {
        object Large {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
        }

        object Medium {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
        }

        object Small {
            val W400 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
            val W500 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
            val W600 = TextStyle(
                fontFamily = FontFamilyPlusJakartaSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        }
    }
}