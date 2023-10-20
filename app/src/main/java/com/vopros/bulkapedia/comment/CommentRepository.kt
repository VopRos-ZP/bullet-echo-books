package com.vopros.bulkapedia.comment

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toComment
import javax.inject.Inject

class CommentRepository @Inject constructor() : FirebaseImpl<Comment>(
    Firebase.firestore.collection("comments"), ::toComment
)