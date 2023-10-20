package com.vopros.bulkapedia.ui.components.topbar

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.IconButton
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.screens.destinations.SettingsScreenDestination
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme
import com.vopros.bulkapedia.ui.theme.BulkapediaTheme
import com.vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun TopBar() {
    val viewModel = LocalTopBarViewModel.current
    val title by viewModel.title.collectAsState()
    val showBack by viewModel.showBack.collectAsState()
    val controller by viewModel.navController.collectAsState()
    TopBar(title = title, showBack = showBack, controller = controller)
}

@Preview(showBackground = true)
@Composable
fun TopBar_Preview() {
    BulkapediaTheme {
        TopBar(
            title = stringResource(R.string.alice),
            showBack = true,
            controller = null
        )
    }
}

@Composable
fun TopBar(title: String, showBack: Boolean, controller: NavController?) {
    TopAppBar(
        backgroundColor = BulkaPediaTheme.colors.primary,
        title = { Text(title = title, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            if (showBack) {
                IconButton(
                    onClick = { controller?.navigateUp() },
                    icon = Icons.Filled.ArrowBack
                )
            }
        },
        actions = {
            IconButton(
                onClick = { controller?.navigate(SettingsScreenDestination()) },
                icon = Icons.Filled.Settings
            )
        }
    )
}