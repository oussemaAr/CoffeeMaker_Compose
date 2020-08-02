package app.elite.coffeemaker.screen

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.foundation.*
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.outlined.AccountBox
import androidx.ui.material.icons.outlined.Home
import androidx.ui.material.icons.outlined.Map
import androidx.ui.res.imageResource
import androidx.ui.text.AnnotatedString
import androidx.ui.text.SpanStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import app.elite.coffeemaker.R
import app.elite.coffeemaker.model.Coffee
import app.elite.coffeemaker.model.coffeeList
import app.elite.coffeemaker.ui.background
import app.elite.coffeemaker.ui.brown500
import app.elite.coffeemaker.ui.brown700
import app.elite.coffeemaker.ui.typography
import app.elite.coffeemaker.utils.FunctionalityNotAvailablePopup
import app.elite.coffeemaker.utils.Screen

@Composable
fun CoffeeListScreen(
        navigateTo: (Screen) -> Unit
) {
    Scaffold(
            backgroundColor = background,
            topBar = { AppBar() },
            bottomBar = { BottomBar(navigateTo) },
            bodyContent = { body(navigateTo) },
    )
}

@Composable
private fun body(navigateTo: (Screen) -> Unit) {
    Column(Modifier.drawBackground(color = background)) {
        Title()
        BodyContent(navigateTo)
    }
}

@Composable
fun BottomBar(navigateTo: (Screen) -> Unit) {

    var showDialog by state { false }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }

    BottomAppBar(
            backgroundColor = Color.White,
            modifier = Modifier
                    .clip(shape = RoundedCornerShape(topRight = 32.dp, topLeft = 32.dp))) {
        Icon(asset = Icons.Outlined.Home,
                modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(onClick = {
                            showDialog = true
                        }))
        Icon(asset = Icons.Outlined.Map,
                modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(onClick = {
                            showDialog = true
                        })
        )
        Icon(asset = Icons.Outlined.AccountBox,
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
                asset = imageResource(id = R.drawable.menu),
                modifier = Modifier.size(24.dp)
        )
        Spacer(
                modifier = Modifier
                        .weight(1f)
        )
        Image(
                asset = imageResource(id = R.drawable.search),
                modifier = Modifier
                        .size(24.dp)

        )
    }
}

@Composable
fun BodyContent(navigateTo: (Screen) -> Unit) {
    LazyColumnItems(items = coffeeList) {
        CoffeeItem(coffee = it, itemCLick = {
            navigateTo(Screen.Details(it.id))
        })
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
                verticalGravity = Alignment.CenterVertically
        ) {
            Image(
                    contentScale = ContentScale.Fit,
                    asset = imageResource(id = coffee.icon),
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
            Image(asset = imageResource(id = R.drawable.back))
        }
    }
}