package com.bluewhaleyt.codewhale.ui.theme

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun CodeWhaleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    BetterSystemBarAppearance(colorScheme = colorScheme, darkTheme = darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
internal fun BetterSystemBarAppearance(
    colorScheme: ColorScheme,
    darkTheme: Boolean
) {
    val context = LocalContext.current
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val windowBackgroundColor = colorScheme.background.toArgb()
            window.setBackgroundDrawable(ColorDrawable(windowBackgroundColor))
            val insetsController = WindowCompat.getInsetsController(window, view)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = Color.Transparent.toArgb()
                insetsController.isAppearanceLightStatusBars = !darkTheme
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                window.navigationBarColor = Color.Transparent.toArgb()
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
}