package com.vopros.bulkapedia.userSet

import com.vopros.bulkapedia.core.Entity
import com.vopros.bulkapedia.gears.GearCell

data class UserSet(
    override val id: String,
    val author: String,
    val gears: Map<GearCell, String>,
    val hero: String,
    val liked: List<String>
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "author" to author,
        "gears" to gears.mapKeys { it.key.name.lowercase() },
        "hero" to hero,
        "liked" to liked,
    )

}
