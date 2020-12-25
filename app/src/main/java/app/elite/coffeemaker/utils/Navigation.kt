package app.elite.coffeemaker.utils

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object SplashScreen : Screen("splash")
    object Details : Screen("details")
}
