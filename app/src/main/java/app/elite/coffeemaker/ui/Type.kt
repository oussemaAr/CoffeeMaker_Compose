package app.elite.coffeemaker.ui

import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.unit.sp
import app.elite.coffeemaker.R

// Set of Material typography styles to start with

private val regular = font(R.font.sen_regular)
private val bold = font(R.font.sen_bold, FontWeight.W600)


val appFontFamily = fontFamily(
    listOf(
        regular,
        bold
    )
)

val typography = Typography(
    body1 =  TextStyle(
        fontFamily = appFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    )
)