package app.elite.coffeemaker.screen

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.layout.Stack
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.height
import androidx.ui.layout.width
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import app.elite.coffeemaker.R
import app.elite.coffeemaker.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
        navigateTo: (Screen) -> Unit
) {
    SplashScreenContent()
    GlobalScope.launch(Dispatchers.Main) {
        delay(5000)
        navigateTo(Screen.Home)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenContent() {
    Stack(modifier = Modifier.fillMaxSize()) {
        Image(
                asset = imageResource(id = R.drawable.background),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
        )
        Image(
                asset = imageResource(id = R.drawable.logo),
                modifier = Modifier
                        .width(125.dp)
                        .height(168.dp)
                        .gravity(Alignment.Center)
        )
    }
}