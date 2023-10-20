package com.vopros.bulkapedia.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.screens.destinations.CategoriesScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.ProfileControllerScreenDestination

enum class BottomNavigationItem(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    WIKI(CategoriesScreenDestination, Icons.Filled.Home, R.string.wiki),
    PROFILE(ProfileControllerScreenDestination, Icons.Filled.Person, R.string.profile);
}