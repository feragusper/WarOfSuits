package com.feragusper.warofsuits.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WarOfSuitsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) ColorPaletteDark else ColorPaletteLight,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
