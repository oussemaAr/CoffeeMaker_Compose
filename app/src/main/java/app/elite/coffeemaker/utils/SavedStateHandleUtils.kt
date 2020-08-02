package app.elite.coffeemaker.utils

import android.os.Bundle
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getMutableStateOf(
        key: String,
        default: T,
        save: (T) -> Bundle,
        restore: (Bundle) -> T
): MutableState<T> {
    val bundle: Bundle? = get(key)
    val initial = if (bundle == null) {
        default
    } else {
        restore(bundle)
    }
    val state = mutableStateOf(initial)
    setSavedStateProvider(key) {
        save(state.value)
    }
    return state
}