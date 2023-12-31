package com.vopros.bulkapedia.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.OutlinedTextField
import com.vopros.bulkapedia.ui.components.PasswordField
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.button.OutlinedButton
import com.vopros.bulkapedia.ui.screens.destinations.ProfileScreenDestination
import com.vopros.bulkapedia.ui.screens.destinations.RegistrationScreenDestination

@Destination
@Composable
fun LoginScreen(navigator: DestinationsNavigator, viewModel: LoginViewModel) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val config by viewModel.config.collectAsState()
    ScreenView(
        title = R.string.login,
        viewModel = viewModel,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_no_bg),
                contentDescription = "logo"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    state = email,
                    label = R.string.enter_email,
                    isError = !email.value.matches("\\w+@\\w+\\.\\w+".toRegex())
                )
                PasswordField(
                    state = password,
                    label = R.string.hint_password
                )
            }
            Button(onClick = { viewModel.login(email.value, password.value) }) {
                Text(R.string.login)
            }
            OutlinedButton(
                onClick = { navigator.navigate(RegistrationScreenDestination()) },
                text = R.string.registration
            )
        }
    }
    LaunchedEffect(config) {
        if (config.second) {
            navigator.navigate(ProfileScreenDestination(config.first))
        }
    }
}