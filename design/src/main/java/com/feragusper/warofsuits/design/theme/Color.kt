package com.feragusper.warofsuits.design.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Green = Color(0xFF388E3C)
val GreenLighter = Color(0xFFC8E6C9)
val White = Color(0xFFFFFFFF)
val Orange = Color(0xFFFF5722)
val Gray = Color(0xFF757575)
val Blacky = Color(0xFF212121)
val Black = Color(0xFF000000)
val Red300 = Color(0xFFE57373)

val ColorPaletteLight = lightColors(
    primary = Green,
    primaryVariant = GreenLighter,
    secondary = Orange,
    background = GreenLighter,
    surface = White,
    error = Red300,
    onError = Red300,
    onSurface = Black,
)

val ColorPaletteDark = darkColors(
    primary = Green,
    background = Blacky,
    surface = Gray,
    error = Red300,
    onError = White,
    onBackground = White,
    onPrimary = White,
    onSurface = White,
)
