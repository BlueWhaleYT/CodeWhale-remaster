package com.bluewhaleyt.codewhale.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.bluewhaleyt.codewhale.R

object AppFont {
    val MainFont = FontFamily(
        Font(R.font.quicksand_medium, weight = FontWeight.Medium),
        Font(R.font.quicksand_semibold, weight = FontWeight.SemiBold)
    )
}

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.MainFont),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.MainFont),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.MainFont),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.MainFont),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.MainFont),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.MainFont),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.MainFont),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.MainFont),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.MainFont),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.MainFont),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.MainFont),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.MainFont),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.MainFont),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.MainFont),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.MainFont)
)