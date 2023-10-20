package com.vopros.bulkapedia.ui.screens.profile

import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.HeroRepository

import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.vopros.bulkapedia.ui.view.ErrViewModel
import com.vopros.bulkapedia.user.User
import com.vopros.bulkapedia.userSet.UserSetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository
): ErrViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    private val _sets = MutableStateFlow<List<List<UserSetUseCase>>?>(null)
    val sets = _sets.asStateFlow()

    fun fetchData(userId: String) {
        listeners["user"] = userRepository.listenOne(userId, Callback(::error) { coroutine { _user.emit(it) } })
        listeners["sets"] = setRepository.listenAll(Callback(::error) {
            coroutine {
                val yourSets = it
                    .filter { s -> s.author == userId }
                    .sortedByDescending { s -> s.liked.size }
                    .map { UserSetUseCase(it,
                        userRepository.fetchOne(it.author),
                        heroRepository.fetchOne(it.hero)
                    ) }
                val favSets = it
                    .filter { s -> s.liked.contains(userId) }
                    .sortedByDescending { s -> s.liked.size }
                    .map { UserSetUseCase(it,
                        userRepository.fetchOne(it.author),
                        heroRepository.fetchOne(it.hero)
                    ) }
                _sets.emit(listOf(yourSets, favSets))
            }
        })
    }

}