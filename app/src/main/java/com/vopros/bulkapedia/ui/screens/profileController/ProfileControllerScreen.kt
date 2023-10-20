package com.vopros.bulkapedia.ui.screens.profileController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vopros.bulkapedia.ui.screens.destinations.LoginScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.ProfileScreenDestination

@RootNavGraph
@Destination
@Composable
fun ProfileControllerScreen(navigator: DestinationsNavigator, viewModel: ProfileControllerViewModel) {
    LaunchedEffect(null) { viewModel.fetchConfig() }
    val config by viewModel.config.collectAsState()
    LaunchedEffect(config) {
        when (config.second) {
            true -> navigator.navigate(ProfileScreenDestination(config.first))
            else -> navigator.navigate(LoginScreenDestination())
        }
    }
}