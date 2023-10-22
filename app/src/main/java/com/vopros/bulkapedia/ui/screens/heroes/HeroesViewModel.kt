package com.vopros.bulkapedia.ui.screens.heroes

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.hero.HeroType
import com.vopros.bulkapedia.ui.components.tags.Tag
import com.vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val heroRepository: HeroRepository
): ErrViewModel() {

    private val _heroes = MutableStateFlow<List<Hero>?>(null)
    val heroes = _heroes.asStateFlow()

    fun fetchHeroes(tag: Tag? = null) {
        listeners["hero"] = heroRepository.listenAll(Callback(::error) {
            coroutine {
                //_heroes.emit(emptyList()) // clear LazyVerticalGrid
                _heroes.emit(it.filter { h -> tag == null || h.type == HeroType.valueOf(tag.id) })
            }
        })
    }

}