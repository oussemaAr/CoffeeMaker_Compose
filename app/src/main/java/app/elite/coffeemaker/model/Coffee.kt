package app.elite.coffeemaker.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Coffee(
        var id: Int,
        var title: String,
        @DrawableRes var icon: Int
) : Serializable