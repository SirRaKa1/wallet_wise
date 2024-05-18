package com.haton.walletwise.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun WalletWiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = BaseLightPalette
    val typography = Typography
    val shape = Shapes

    CompositionLocalProvider(
        LocalWiseColors provides colors,
        LocalWiseTypography provides typography,
        LocalWiseShape provides shape,
        content = content
    )
}
