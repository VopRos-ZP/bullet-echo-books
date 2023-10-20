package com.vopros.bulkapedia.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun TextSnackbar(hostState: SnackbarHostState, modifier: Modifier = Modifier) {
    SnackbarHost(hostState, modifier) {
        Snackbar(
            snackbarData = it,
            shape = RoundedCornerShape(5.dp),
            backgroundColor = BulkaPediaTheme.colors.primary,
            contentColor = BulkaPediaTheme.colors.white,
            actionColor = BulkaPediaTheme.colors.tintColor
        )
    }
}