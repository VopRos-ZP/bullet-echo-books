package com.vopros.bulkapedia.ui.screens.maps

import com.vopros.bulkapedia.map.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.ui.components.tags.Tag
import com.vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository
): ErrViewModel() {

    private val _maps = MutableStateFlow(emptyList<GameMap>())
    val maps = _maps.asStateFlow()

    fun fetchMaps(tag: Tag? = null) {
        coroutine {
            _maps.emit(emptyList()) // clear LazyColumn
            _maps.emit(mapRepository.fetchAll().filter { m -> m.mode == tag?.id || tag == null })
        }
    }

}