package com.vopros.bulkapedia.ui.screens.profile

import com.vopros.bulkapedia.hero.HeroRepository

import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository
): ErrViewModel() {

//    private val _user = MutableStateFlow<User?>(null)
//    private val _sets = MutableStateFlow<List<UserSet>>(emptyList())
//
//    private var userListener: ListenerRegistration? = null
//    private var setsListener: ListenerRegistration? = null
//
//    override var reducer: Reducer<ProfileViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is ProfileViewIntent.Start -> init(intent.userId)
//            is ProfileViewIntent.Dispose -> dispose()
//        }
//    }
//
//    private suspend fun init(userId: String) {
//        Log.d(this.javaClass.simpleName, userId)
//        userListener = userRepository.listenOne(userId, Callback(this::error) {
//            viewModelScope.launch { _user.emit(it) }
//        })
//        setsListener = setRepository.listenAll(Callback(this::error) {
//            viewModelScope.launch {
//                val yourSets = it
//                    .filter { s -> s.author == userId }
//                    .sortedByDescending { s -> s.liked.size }
////                val favSets = it
////                    .filter { s -> s.liked.contains(userId) }
////                    .sortedByDescending { s -> s.liked.size }
//                _sets.emit(yourSets)
//            }
//        })
//        _user.combine(_sets) { user, sets -> Pair(user, sets) }
//            .collect { (user, sets) ->
//                if (user != null) {
//                    success(Pair(user, sets.map {
//                        UserSetUseCase(it,
//                            userRepository.fetchOne(it.author),
//                            heroRepository.fetchOne(it.hero)
//                        )
//                    }))
//                }
//            }
//    }
//
//    private fun dispose() {
//        userListener?.remove()
//        setsListener?.remove()
//    }

}