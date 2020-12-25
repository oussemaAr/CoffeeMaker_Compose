package app.elite.coffeemaker.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import app.elite.coffeemaker.R
import app.elite.coffeemaker.model.Coffee
import app.elite.coffeemaker.model.coffeeList
import app.elite.coffeemaker.ui.background
import app.elite.coffeemaker.ui.brown500
import app.elite.coffeemaker.ui.typography
import app.elite.coffeemaker.utils.FunctionalityNotAvailablePopup
import app.elite.coffeemaker.utils.Screen

@Composable
fun CoffeeListScreen(
    navController: NavController
) {
    Scaffold(
        backgroundColor = background,
        topBar = { AppBar() },
        bottomBar = { BottomBar() },
        bodyContent = { Body(navController) },
    )
}

@Composable
private fun Body(navController: NavController) {
    Column(Modifier.background(color = background)) {
        Title()
        BodyContent(navController)
    }
}

@Composable
fun BottomBar() {

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }

    BottomAppBar(
        backgroundColor = Color.White,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topRight = 32.dp, topLeft = 32.dp))
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(onClick = {
                    showDialog = true
                })
        )
        Icon(
            imageVector = Icons.Outlined.Map,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable(onClick = {
                    showDialog = true
                })
        )
        Icon(
            imageVector = Icons.Outlined.AccountBox,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable {
                    showDialog = true
                })
    }
}


@Preview(name = "Page title")
@Composable
fun Title() {
    val ch = "It's Great Day for\nCoffee."
    val text = AnnotatedString.Builder(ch).apply {
        addStyle(SpanStyle(color = brown500, fontWeight = FontWeight.Bold), 11, ch.length)
    }
    Text(
        modifier = Modifier.padding(24.dp),
        fontSize = 36.sp,
        color = Color.Black,
        text = text.toAnnotatedString()
    )
}

@Preview
@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .height(56.dp)
            .padding(16.dp)
    ) {
        Image(
            bitmap = imageResource(id = R.drawable.menu),
            modifier = Modifier.size(24.dp)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Image(
            bitmap = imageResource(id = R.drawable.search),
            modifier = Modifier
                .size(24.dp)

        )
    }
}

@Composable
fun BodyContent(navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
        items(items = coffeeList) {
            CoffeeItem(coffee = it, itemCLick = {
                navController.navigate("${Screen.Details.route}/${it.id}")
            })
        }
    }
}

@Composable
fun CoffeeItem(coffee: Coffee, itemCLick: () -> Unit) {
    ListItem(
        modifier = Modifier
            .padding(bottom = 48.dp)
            .clickable(onClick = itemCLick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                contentScale = ContentScale.Fit,
                bitmap = imageResource(id = coffee.icon),
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
            Text(
                fontSize = 20.sp,
                style = typography.body1,
                text = coffee.title,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 32.dp)
            )
            Image(bitmap = imageResource(id = R.drawable.back))
        }
    }
}