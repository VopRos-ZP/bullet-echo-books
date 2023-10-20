package com.vopros.bulkapedia.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.Direction
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.screens.destinations.CategoriesScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.LoginScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.ProfileScreenDestination

sealed class BottomNavigationItem<T>(
    val direction: () -> Direction,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    object WIKI: BottomNavigationItem<Unit>({ CategoriesScreenDestination() }, Icons.Filled.Home, R.string.wiki)
    object LOGIN: BottomNavigationItem<Unit>({ LoginScreenDestination() }, Icons.Filled.Login, R.string.login)
    data class PROFILE(val userId: String) : BottomNavigationItem<ProfileScreenDestination.NavArgs>(
        { ProfileScreenDestination(userId) }, Icons.Filled.Person, R.string.profile)
}