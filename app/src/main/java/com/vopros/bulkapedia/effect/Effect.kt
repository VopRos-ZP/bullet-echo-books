package com.vopros.bulkapedia.effect


data class Effect(
    val number: Int,
    val percent: Boolean,
    val description: String
) {

     fun toData(): Map<String, Any> = mapOf(
        "number" to number,
        "percent" to percent,
        "description" to description,
    )

}
