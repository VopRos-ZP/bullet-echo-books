package com.vopros.bulkapedia.effect

import com.google.firebase.firestore.DocumentId

data class EffectDTO(
    @DocumentId var id: String = "",
    var number: Int = 0,
    var percent: Boolean = false,
    var description: String = ""
)
