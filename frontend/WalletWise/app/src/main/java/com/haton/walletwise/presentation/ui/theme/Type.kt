package com.haton.walletwise.presentation.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val Normal = FontWeight.Normal
val Medium = FontWeight.Medium
val SemiBold = FontWeight.SemiBold
val Bold = FontWeight.Bold

val Typography = WiseTypography(
    appTitle = TextStyle(
        fontFamily = Manrope,
        fontWeight = Bold,
        fontSize = 24.sp,
        color = BaseLightPalette.blackText
    ),
    buttonText = TextStyle(
        fontFamily = Manrope,
        fontWeight = SemiBold,
        fontSize = 16.sp,
        color = BaseLightPalette.blackText
    ),
    fadedText = TextStyle(
        fontFamily = Manrope,
        fontWeight = Medium,
        fontSize = 16.sp,
        color = BaseLightPalette.fadedText
    ),
    fieldText = TextStyle(
        fontFamily = Manrope,
        fontWeight = Normal,
        fontSize = 14.sp,
        color = BaseLightPalette.blackText
    ),
    underlinedText = TextStyle(
        fontFamily = Manrope,
        fontWeight = Medium,
        fontSize = 16.sp,
        color = BaseLightPalette.blackText,
        textDecoration = TextDecoration.Underline
    ),
)