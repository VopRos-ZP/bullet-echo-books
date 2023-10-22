package com.vopros.bulkapedia.ui.screens.createSet

import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.gears.Gear
import com.vopros.bulkapedia.gears.GearCell
import com.vopros.bulkapedia.gears.GearRepository
import com.vopros.bulkapedia.gears.GearSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.hero.HeroType
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
    private val gearRepository: GearRepository,
) : ErrViewModel() {

    private val _useCase = MutableStateFlow<UserSetUseCase?>(null)
    val useCase = _useCase.asStateFlow()

    private val _gears = MutableStateFlow(emptyList<Gear>())
    val gears = _gears.asStateFlow()

    fun init(heroId: String, setId: String?) {
        coroutine {
            dataStore.userId.collect { token ->
                _useCase.emit(
                    UserSetUseCase(
                        user = userRepository.fetchOne(token),
                        hero = heroRepository.fetchOne(heroId),
                        set = when (setId) {
                            null -> UserSet("", token, Gear.emptyGears, heroId, emptyList())
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

    fun unselectGears() {
        coroutine { _gears.emit(emptyList()) }
    }

    fun selectGear(gearCell: GearCell, heroId: String) {
        listeners["gears"] = gearRepository.listenAll(Callback(::error) {
            coroutine {
                val hero = heroRepository.fetchOne(heroId)
                val heroGears = it
                    .filter { g -> g.gearCell == gearCell && (
                            g.gearSet == GearSet.NONE ||
                            g.gearSet == fetchGearTypeByHeroType(hero.type) ||
                                    hero.personalGears.contains(g.id))
                    }.sortedBy { it.gearSet }
                _gears.emit(heroGears)
            }
        })
    }

    private fun fetchGearTypeByHeroType(type: HeroType): GearSet {
        return when (type) {
            HeroType.SHORTGUN -> GearSet.WHITE_INDEX
            HeroType.SCOUT -> GearSet.PART
            HeroType.SNIPER -> GearSet.DARK_IMPLANT
            HeroType.TANK -> GearSet.HEAVY_PORT
            HeroType.TROOPER -> GearSet.BIO_NODE
        }
    }

}