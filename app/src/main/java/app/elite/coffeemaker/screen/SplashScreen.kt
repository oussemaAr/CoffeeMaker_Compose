package app.elite.coffeemaker.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import app.elite.coffeemaker.R
import app.elite.coffeemaker.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navController: NavController
) {
    SplashScreenContent()
    GlobalScope.launch(Dispatchers.Main) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = imageResource(id = R.drawable.background),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            bitmap = imageResource(id = R.drawable.logo),
            modifier = Modifier
                .width(125.dp)
                .height(168.dp)
                .align(Alignment.Center)
        )
    }
}