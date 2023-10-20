package com.vopros.bulkapedia.ui.theme

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.vopros.bulkapedia.ui.components.topbar.TopBarViewModel

val LocalBulkaPediaColors = staticCompositionLocalOf<BulkaPediaColors> {
    error("BulkaPediaColors doesn't provides")
}

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController doesn't provides")
}

val LocalTopBarViewModel = compositionLocalOf<TopBarViewModel> {
    error("TopBarViewModel doesn't provides")
}

val LocalSnackbar = compositionLocalOf<SnackbarHostState?> { null }