package app.elite.coffeemaker.screen

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.BlendMode
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.Button
import androidx.ui.material.Card
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import app.elite.coffeemaker.R
import app.elite.coffeemaker.model.Coffee
import app.elite.coffeemaker.model.coffeeList
import app.elite.coffeemaker.ui.brown500
import app.elite.coffeemaker.ui.typography
import app.elite.coffeemaker.utils.FunctionalityNotAvailablePopup


@Composable
fun DetailsScreen(
        coffee: Int
) {
    DetailsScreenContent(coffeeList[coffee - 1])
}

@Composable
fun DetailsScreenContent(coffee: Coffee) {
    var showDialog by state { false }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }
    Column(
            modifier = Modifier
                    .fillMaxHeight()
                    .drawBackground(color = Color.White)
    ) {
        ToolBar(coffee.title)
        Spacer(modifier = Modifier.height(16.dp))
        OrderView(coffee.title)
        Spacer(modifier = Modifier.height(16.dp))
        OrderDetails()
        Spacer(modifier = Modifier.weight(1f))
        Button(
                onClick = {
                    showDialog = true
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.gravity(Alignment.CenterHorizontally)

        ) {
            Text(
                    text = "Add to Cart",
                    fontSize = 20.sp,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun OrderDetails() {
    Column(
            modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(text = "Size")
        Spacer(modifier = Modifier.height(8.dp))
        SizeBox()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Sugar (in Cubes)")
        Spacer(modifier = Modifier.height(8.dp))
        SugarBox()
    }

}

@Composable
private fun SugarBox() {
    var pos by state { 1 }
    Row(
            verticalGravity = Alignment.Bottom
    ) {
        SugarItem(1, pos == 0) {
            pos = 0
        }
        Spacer(modifier = Modifier.width(8.dp))
        SugarItem(2, pos == 1) {
            pos = 1
        }
        Spacer(modifier = Modifier.width(8.dp))
        SugarItem(3, pos == 2) {
            pos = 2
        }
    }
}

@Composable
fun SugarItem(count: Int, pos: Boolean, click: () -> Unit) {

    Column(
            modifier = Modifier
                    .clickable(onClick = click)
                    .padding(8.dp),
    ) {
        repeat(count) {
            Image(
                    asset = imageResource(id = R.drawable.cube),
                    modifier = Modifier.size(24.dp),
                    colorFilter = if (pos) ColorFilter(Color.Black, BlendMode.srcIn) else null
            )
        }
    }
}

@Composable
private fun SizeBox() {
    var pos by state { 1 }
    Row(verticalGravity = Alignment.Bottom) {
        Image(
                asset = imageResource(id = R.drawable.empty_big),
                modifier = Modifier
                        .size(48.dp)
                        .padding(4.dp)
                        .clickable(onClick = {
                            pos = 0
                        }),
                colorFilter = if (pos == 0) ColorFilter(Color.Black, BlendMode.srcIn) else null
        )
        Image(
                asset = imageResource(id = R.drawable.empty_big),
                modifier = Modifier
                        .size(56.dp)
                        .padding(4.dp)
                        .clickable(onClick = {
                            pos = 1
                        }),
                colorFilter = if (pos == 1) ColorFilter(Color.Black, BlendMode.srcIn) else null
        )
        Image(
                asset = imageResource(id = R.drawable.empty_big),
                modifier = Modifier
                        .size(64.dp)
                        .padding(4.dp)
                        .clickable(onClick = {
                            pos = 2
                        }),
                colorFilter = if (pos == 2) ColorFilter(Color.Black, BlendMode.srcIn) else null
        )
    }
}

@Composable
fun ToolBar(title: String) {
    Stack(
            modifier = Modifier
                    .fillMaxWidth()
                    .drawBackground(
                            shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp),
                            color = Color.White
                    )
    ) {
        Image(
                asset = imageResource(id = R.drawable.details_background),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                        .fillMaxWidth()
                        .drawShadow(
                                shape = RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp),
                                elevation = 2.dp
                        )
        )
        Row {
            Image(
                    asset = imageResource(id = R.drawable.back),
                    alignment = Alignment.CenterStart
            )
            Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = typography.body1,
                    fontSize = 23.sp
            )
        }
        Image(
                asset = imageResource(id = R.drawable.macciato_big),
                modifier = Modifier
                        .gravity(Alignment.Center)
                        .width(86.dp)
                        .height(128.dp)
        )
    }
}

@Composable
fun OrderView(title: String) {
    var count by state { 0 }
    Row(
            modifier = Modifier.padding(16.dp),
            verticalGravity = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                    text = title,
                    color = Color(0xFF2D140D)
            )
            Text(
                    text = "15$",
                    color = brown500,
                    fontSize = 32.sp
            )
        }
        Card(shape = RoundedCornerShape(8.dp)) {
            Row {
                ControlItem(" - ") {
                    count--
                }
                Text(
                        text = count.toString(),
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .gravity(Alignment.CenterVertically)
                )
                ControlItem(" + ") {
                    count++
                }
            }
        }
    }
}

@Composable
private fun ControlItem(text: String, click: () -> Unit) {
    Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                    .drawBackground(color = brown500)
                    .padding(8.dp)
                    .size(24.dp)
                    .gravity(align = Alignment.CenterVertically)
                    .clickable(onClick = click)
    )
}