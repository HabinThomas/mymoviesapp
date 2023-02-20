package com.example.mysamplemovieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Gray900,
    secondary = Rust600,
    background = Primary,
    surface = Color.White.copy(alpha = .85f),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Taupe800,
    onSurface = Gray900.copy(alpha = 0.8f)
)

private val DarkColorPalette = darkColors(
    primary = Color.White,
    secondary = Rust300,
    background = Gray900,
    surface = Color.White.copy(alpha = 0.15f),
    onPrimary = Gray900,
    onSecondary = Gray900,
    onBackground = Primary,
    onSurface = Color.White.copy(alpha = .8f)
)

@Composable
fun MyAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
