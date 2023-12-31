package com.vopros.bulkapedia.ui.screens.maps

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.components.tags.Tag
import com.vopros.bulkapedia.ui.components.tags.Tags
import com.vopros.bulkapedia.ui.components.tags.mapsTags
import com.vopros.bulkapedia.ui.screens.destinations.MapScreenDestination

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun MapsScreen(navigator: DestinationsNavigator, viewModel: MapsViewModel) {
    val tags = mapsTags()
    val maps by viewModel.maps.collectAsState()
    var selectedTag by remember { mutableStateOf<Tag?>(null) }
    ScreenView(
        title = R.string.select_map,
        showBack = true,
        viewModel = viewModel,
        key = selectedTag,
        fetch = { fetchMaps(selectedTag) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Tags(tags, selectedTag) { selectedTag = if (selectedTag == it) null else it }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(maps) { MapCard(Modifier.animateItemPlacement(), it) {
                    navigator.navigate(MapScreenDestination(it.id))
                } }
            }
        }
    }
}

@Composable
fun MapCard(
    modifier: Modifier = Modifier,
    map: GameMap,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) { Image(url = map.image) }
}