package com.vopros.bulkapedia.ui.screens.comments

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.vopros.bulkapedia.comment.Comment
import com.vopros.bulkapedia.comment.CommentRepository
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.ErrViewModel
import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSetUseCase
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository,
    private val heroRepository: HeroRepository,
    private val userRepository: UserRepository,
    private val commentRepository: CommentRepository
): ErrViewModel() {

    private val _author = MutableStateFlow("")
    val author = _author.asStateFlow()

    private val _set = MutableStateFlow<UserSetUseCase?>(null)
    val set = _set.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>?>(null)
    val comments = _comments.asStateFlow()

    fun fetchComments(setId: String) {
        listeners["set"] = setRepository.listenOne(setId, Callback(::error) {
            coroutine {
                _set.emit(
                    UserSetUseCase(set = it,
                    user = userRepository.fetchOne(it.author),
                    hero = heroRepository.fetchOne(it.hero)
                )
                )
            }
        })
        listeners["comment"] = commentRepository.listenAll(Callback(::error) {
            coroutine {
                val comments = it
                    .filter { c -> c.set == setId }
                    .sortedWith { c1, c2 -> c1.date.compareTo(c2.date) }
                _comments.emit(comments)
                Log.d("CommentsViewModel", "$it")
            }
        })
        coroutine { dataStore.userId.collect { _author.emit(it) } }
    }

    fun sendComment(comment: Comment) {
        coroutine { commentRepository.update(comment) }
    }

}