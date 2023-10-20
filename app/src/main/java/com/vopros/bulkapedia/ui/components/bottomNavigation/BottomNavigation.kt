package com.vopros.bulkapedia.ui.components.bottomNavigation

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.navigation.BottomNavigationItem
import com.vopros.bulkapedia.ui.screens.NavGraphs
import com.vopros.bulkapedia.ui.screens.appCurrentDestinationAsState
import com.vopros.bulkapedia.ui.screens.startAppDestination
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun BottomNavigation(navController: NavController, viewModel: BottomNavigationViewModel = hiltViewModel()) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    LaunchedEffect(null) { viewModel.fetchConfig() }
    val config by viewModel.config.collectAsState()
    val items = when (config.second) {
        false -> listOf(BottomNavigationItem.WIKI, BottomNavigationItem.LOGIN)
        else -> listOf(BottomNavigationItem.WIKI, BottomNavigationItem.PROFILE(config.first))
    }
    androidx.compose.material.BottomNavigation(
        backgroundColor = BulkaPediaTheme.colors.primary,
    ) {
        items.map { item ->
            BottomNavigationItem(
                selected = currentDestination == item.direction,
                label = { Text(item.label) },
                icon = { Icon(item.icon, contentDescription = null, tint = BulkaPediaTheme.colors.tintColor) },
                onClick = {
                    navController.navigate(item.direction()) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
