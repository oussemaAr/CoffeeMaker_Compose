package app.elite.coffeemaker.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = brown500,
    primaryVariant = brown700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = brown500,
    primaryVariant = brown700,
    secondary = teal200,
    background = background
)

@Composable
fun CoffeeMakerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}