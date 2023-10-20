package com.vopros.bulkapedia.gears

import com.vopros.bulkapedia.core.Entity

data class Gear(
    override val id: String,
    val gearCell: String,
    val gearSet: String,
    val image: String
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "gearCell" to gearCell,
        "gearSet" to gearSet,
        "image" to image
    )

}
