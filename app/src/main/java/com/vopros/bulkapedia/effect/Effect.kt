package com.vopros.bulkapedia.effect

import com.vopros.bulkapedia.core.Entity

data class Effect(
    override val id: String,
    val number: Int,
    val percent: Boolean,
    val description: String
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "number" to number,
        "percent" to percent,
        "description" to description,
    )

}
