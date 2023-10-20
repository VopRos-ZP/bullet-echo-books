package com.vopros.bulkapedia.ui.screens.registration

import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import com.vopros.bulkapedia.firebase.AuthRepository
import com.vopros.bulkapedia.ui.view.ErrViewModel
import com.vopros.bulkapedia.user.User
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ErrViewModel() {

    fun register(
        login: String,
        password: String,
        nickname: String,
        onSuccess: () -> Unit
    ) {
        coroutine {
            authRepository.register(
                User(
                "", false,
                login, nickname, password,
                Timestamp.now(), Timestamp.now()
            ), ::error) { onSuccess() }
        }
    }

}