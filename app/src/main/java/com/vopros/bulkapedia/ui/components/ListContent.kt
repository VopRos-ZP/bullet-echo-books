package com.vopros.bulkapedia.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun <T> ListContent(
    items: List<T>?,
    sizing: @Composable (@Composable () -> Unit) -> Unit = { CenterBox { it() } },
    content: @Composable (List<T>) -> Unit
) {
    when (items) {
        null -> sizing { CircularProgressIndicator(color = BulkaPediaTheme.colors.tintColor) }
        emptyList<T>() -> sizing { Text(resource = R.string.empty_sets) }
        else -> content(items)
    }
}