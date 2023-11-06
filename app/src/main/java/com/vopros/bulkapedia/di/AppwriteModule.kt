package com.vopros.bulkapedia.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.appwrite.Client
import io.appwrite.services.Account
import javax.inject.Inject

class Appwrite @Inject constructor(@ApplicationContext private val context: Context) {

    val client = Client(context)
        .setEndpoint("https://cloud.appwrite.io/v1")
        .setProject("65492a52d84bbfad591a")
        .setSelfSigned(status = true) // For self signed certificates, only use for development

}