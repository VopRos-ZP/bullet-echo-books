package com.vopros.bulkapedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vopros.bulkapedia.ui.components.Home
import com.vopros.bulkapedia.ui.theme.BulkapediaTheme
import com.vopros.bulkapedia.utils.ResourceManager
import com.vopros.bulkapedia.utils.resourceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resourceManager = ResourceManager()
        setContent { BulkapediaTheme { Home() } }
    }

}