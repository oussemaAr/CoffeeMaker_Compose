package app.elite.coffeemaker.ui

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

private val DarkColorPalette = darkColorPalette(
        primary = brown500,
        primaryVariant = brown700,
        secondary = teal200
)

private val LightColorPalette = lightColorPalette(
        primary = brown500,
        primaryVariant = brown700,
        secondary = teal200,
        background = background
)

@Composable
fun CoffeeMakerTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable() () -> Unit
) {
    MaterialTheme(
            colors = LightColorPalette,
            typography = typography,
            shapes = shapes,
            content = content
    )
}