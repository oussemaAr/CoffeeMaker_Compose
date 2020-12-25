package app.elite.coffeemaker.utils

import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle

@Deprecated("New jetpack compose navigation support")
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