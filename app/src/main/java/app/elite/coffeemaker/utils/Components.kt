package app.elite.coffeemaker.utils

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.AlertDialog
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TextButton

@Composable
fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
    AlertDialog(
            onCloseRequest = onDismiss,
            text = {
                Text(
                        text = "Functionality not available \uD83D\uDE48",
                        style = MaterialTheme.typography.body2
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "CLOSE")
                }
            }
    )
}
