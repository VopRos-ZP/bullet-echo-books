package com.vopros.bulkapedia.hero

import com.vopros.bulkapedia.core.Entity

data class Hero(
    override val id: String,
    val active: Boolean,
    val difficult: String,
    val image: String,
    val type: HeroType,
    val counterpicks: List<String>,
    val stats: Map<String, Double>,
    val personalGears: List<String>
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "active" to active,
        "difficult" to difficult,
        "image" to image,
        "type" to type.name.lowercase(),
        "counterpicks" to counterpicks,
        "stats" to stats,
        "personalGears" to personalGears
    )

    companion object {

        val EMPTY = Hero(
            id = "",
            active = true,
            difficult = "",
            image = "",
            type = HeroType.SHORTGUN,
            counterpicks = emptyList(),
            stats = emptyMap(),
            personalGears = emptyList()
        )

    }

}
