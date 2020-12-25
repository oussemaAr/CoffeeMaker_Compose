package app.elite.coffeemaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.elite.coffeemaker.screen.CoffeeListScreen
import app.elite.coffeemaker.screen.DetailsScreen
import app.elite.coffeemaker.screen.SplashScreen
import app.elite.coffeemaker.ui.CoffeeMakerTheme
import app.elite.coffeemaker.utils.Screen


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.SplashScreen.route
            ) {
                composable(Screen.SplashScreen.route) { BaseComponent { SplashScreen(navController = navController) } }
                composable(Screen.Home.route) { BaseComponent { CoffeeListScreen(navController = navController) } }
                composable("${Screen.Details.route}/{id}") {
                    BaseComponent {
                        DetailsScreen(coffee = it.arguments?.getString("id", "1")?.toInt()!!)
                    }
                }
            }
        }
    }
}

@Composable
fun BaseComponent(content: @Composable () -> Unit) {
    CoffeeMakerTheme {
        content()
    }
}

