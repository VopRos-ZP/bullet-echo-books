package com.vopros.bulkapedia.gears

import com.vopros.bulkapedia.effect.Effect

data class GearDTO(
    var id: String = "",
    var gearCell: String = "",
    var gearSet: String = "",
    var icon: String = "",
    var effects: Map<String, Effect> = emptyMap(),
    var ranks: Map<String, List<Int>> = emptyMap()
)
