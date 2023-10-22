package com.vopros.bulkapedia.effect

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toEffect
import javax.inject.Inject

class EffectRepository @Inject constructor() : FirebaseImpl<Effect>(
    Firebase.firestore.collection("effects"), ::toEffect
)