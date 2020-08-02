package app.elite.coffeemaker.utils

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

enum class ScreenName { SPLASH, HOME, COFFEE }

sealed class Screen(val id: ScreenName) {
    object Home : Screen(ScreenName.HOME)
    object SplashScreen : Screen(ScreenName.SPLASH)
    data class Details(val item: Int) : Screen(ScreenName.COFFEE)
}

private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_COFFEE = "coffee"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        if (this is Screen.Details) {
            it.putSerializable(SIS_COFFEE, item)
        }
    }
}

private fun Bundle.toScreen(): Screen {
    return when (ScreenName.valueOf(getStringOrThrow(SIS_NAME))) {
        ScreenName.SPLASH -> Screen.SplashScreen
        ScreenName.HOME -> Screen.Home
        ScreenName.COFFEE -> {
            val id = getInt(SIS_COFFEE)
            Screen.Details(id)
        }
    }
}

private fun Bundle.getStringOrThrow(key: String) =
        requireNotNull(getString(key)) { "Missing key '$key' in $this" }

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf(
            key = SIS_SCREEN,
            default = Screen.SplashScreen,
            save = { it.toBundle() },
            restore = { it.toScreen() }
    )
        private set

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Home
        currentScreen = Screen.Home
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}

