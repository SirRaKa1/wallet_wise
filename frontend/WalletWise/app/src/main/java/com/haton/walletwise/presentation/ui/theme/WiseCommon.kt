package com.haton.walletwise.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.haton.walletwise.R

data class WiseColors(
    val white: Color,
    val black: Color,
    val background: Color,
    val primary: Color,
    val blackText: Color,
    val fadedText: Color,
    val gradientStart: Color,
    val gradientEnd: Color,
)

data class WiseTypography(
    val appTitle: TextStyle,
    val buttonText: TextStyle,
    val fadedText: TextStyle,
    val fieldText: TextStyle,
    val underlinedText: TextStyle,
)

data class WiseShape(
    val big: Shape,
)

val Manrope = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_semibold, FontWeight.SemiBold),
    Font(R.font.manrope_bold, FontWeight.Bold),
)

object WiseCommon {
    val colors: WiseColors
        @Composable
        get() = LocalWiseColors.current

    val typography: WiseTypography
        @Composable
        get() = LocalWiseTypography.current

    val shape: WiseShape
        @Composable
        get() = LocalWiseShape.current
}

val LocalWiseColors = staticCompositionLocalOf<WiseColors> {
    error("No colors provided")
}

val LocalWiseTypography = staticCompositionLocalOf<WiseTypography> {
    error("No font provided")
}

val LocalWiseShape = staticCompositionLocalOf<WiseShape> {
    error("No shapes provided")
}
