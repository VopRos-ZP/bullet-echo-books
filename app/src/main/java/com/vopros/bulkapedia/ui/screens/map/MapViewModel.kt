package com.vopros.bulkapedia.ui.screens.map

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.map.MapRepository
import com.vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
): ErrViewModel() {

    private val _map = MutableStateFlow<GameMap?>(null)
    val map = _map.asStateFlow()

    fun fetch(mapId: String) {
        listeners["map"] = mapRepository.listenOne(mapId, Callback(::error) {
            coroutine { _map.emit(it) }
        })
    }

}