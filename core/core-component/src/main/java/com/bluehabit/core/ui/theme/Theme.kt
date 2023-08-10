/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.core.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat


private val lightColorScheme = lightColors(
    primary = Primary600,
    onPrimary = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onBackground = Gray900,
    onSurface = Gray900,
    onError = Color.White,
    secondary = BlueLight600,
    onSecondary = BlueLight700,
    secondaryVariant = BlueGray600,
    primaryVariant = Success700,
    error = Error700,
)

private val shapeScheme = Shapes(
    large = RoundedCornerShape(24.dp),
    medium = RoundedCornerShape(18.dp),
    small = RoundedCornerShape(10.dp)
)

@Composable
fun BudgetKuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> lightColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content,
        shapes = shapeScheme
    )
}