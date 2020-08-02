package app.elite.coffeemaker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import app.elite.coffeemaker.screen.CoffeeListScreen
import app.elite.coffeemaker.screen.DetailsScreen
import app.elite.coffeemaker.screen.SplashScreen
import app.elite.coffeemaker.ui.CoffeeMakerTheme
import app.elite.coffeemaker.utils.NavigationViewModel
import app.elite.coffeemaker.utils.Screen

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoffeeMakerTheme {
                Crossfade(current = navigationViewModel.currentScreen) { screen ->

                    when (screen) {
                        is Screen.SplashScreen -> {
                            SplashScreen(
                                    navigateTo = navigationViewModel::navigateTo
                            )
                        }
                        is Screen.Home -> {
                            CoffeeListScreen(
                                    navigateTo = navigationViewModel::navigateTo
                            )
                        }
                        is Screen.Details -> {
                            DetailsScreen(
                                    screen.item
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!navigationViewModel.onBack()) {
            super.onBackPressed()
        }
    }
}
