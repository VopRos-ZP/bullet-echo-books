package com.vopros.bulkapedia.ui.screens.hero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.Loading
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.button.OutlinedButton
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.components.tab.Tab
import com.vopros.bulkapedia.ui.components.tab.TabRowWithPager
import com.vopros.bulkapedia.ui.components.userSet.UserSetCard
import com.vopros.bulkapedia.ui.screens.destinations.CommentsScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.CreateSetScreenDestination
import com.vopros.bulkapedia.ui.theme.LocalSnackbar
import com.vopros.bulkapedia.utils.resourceManager
import kotlinx.coroutines.launch

@Destination
@Composable
fun HeroScreen(
    navigator: DestinationsNavigator,
    viewModel: HeroViewModel,
    heroId: String
) {
    val hero by viewModel.hero.collectAsState()
    val sets by viewModel.sets.collectAsState()
    val config by viewModel.config.collectAsState()
    val snackbar = LocalSnackbar.current
    val scope = rememberCoroutineScope()
    val loginMessageText = stringResource(R.string.need_login_for_use_it)
    ScreenView(
        title = resourceManager.toSource(heroId),
        showBack = true,
        viewModel = viewModel,
        fetch = { fetch(heroId) }
    ) {
        when (hero) {
            null -> Loading()
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    /* Hero icon with difficult */
                    item {
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Image(url = hero!!.image, modifier = Modifier.height(150.dp))
                        }
                    }
                    /* UserSets */
                    item {
                        TabRowWithPager(
                            listOf(Tab(R.string.one), Tab(R.string.two), Tab(R.string.three)), sets
                        ) { HCenterBox { UserSetCard(it) {
                            navigator.navigate(CommentsScreenDestination(it.set.id))
                        } } }
                    }
                    /* Add user set Button */
                    item {
                        OutlinedButton(onClick = {
                            if (config.second) {
                                navigator.navigate(CreateSetScreenDestination(heroId, null))
                            } else {
                                scope.launch {
                                    snackbar?.showSnackbar(loginMessageText)
                                }
                            }
                        }, text = R.string.create_set)
                    }
                }
            }
        }
    }
}

@Composable
fun HeroThumbnail(
    image: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(url = image, modifier = Modifier.size(150.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                content = content
            )
        }
    }
}
