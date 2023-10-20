package com.vopros.bulkapedia.ui.screens.hero

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.ui.view.ErrViewModel
import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSetUseCase
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository
): ErrViewModel() {

    private val _hero = MutableStateFlow<Hero?>(null)
    val hero = _hero.asStateFlow()

    private val _sets = MutableStateFlow<List<UserSetUseCase>?>(null)
    val sets = _sets.asStateFlow()

    fun fetch(heroId: String) {
        listeners["hero"] = heroRepository.listenOne(heroId, Callback(::error) {
            coroutine { _hero.emit(it) }
        })
        listeners["set"] = setRepository.listenAll(Callback(::error) {
            coroutine {
                val top3 = it
                    .filter { s -> s.hero == heroId }
                    .sortedByDescending { s -> s.liked.count() }
                    .take(3)
                    .map { UserSetUseCase(it,
                        userRepository.fetchOne(it.author),
                        heroRepository.fetchOne(it.hero))
                    }
                _sets.emit(top3)
            }
        })
    }

}