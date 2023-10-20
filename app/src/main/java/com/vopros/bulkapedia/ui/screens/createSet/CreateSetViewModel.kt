package com.vopros.bulkapedia.ui.screens.createSet

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.ErrViewModel
import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetUseCase
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository,
) : ErrViewModel() {

    private val _useCase = MutableStateFlow<UserSetUseCase?>(null)
    val useCase = _useCase.asStateFlow()

    fun init(heroId: String, setId: String?) {
        coroutine {
            dataStore.userId.collect { token ->
                _useCase.emit(
                    UserSetUseCase(
                    user = userRepository.fetchOne(token),
                    hero = heroRepository.fetchOne(heroId),
                    set = when (setId) {
                        null -> UserSet("", token, emptyMap(), heroId, emptyList())
                        else -> setRepository.fetchOne(setId)
                    }
                )
                )
            }
        }
    }

    fun saveSet(userSet: UserSet) {
        coroutine { setRepository.update(userSet) }
    }

}