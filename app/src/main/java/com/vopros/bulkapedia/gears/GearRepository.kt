package com.vopros.bulkapedia.gears

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toGear
import javax.inject.Inject

class GearRepository @Inject constructor(): FirebaseImpl<Gear>(
    Firebase.firestore.collection(""), ::toGear
)