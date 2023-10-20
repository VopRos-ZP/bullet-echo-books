package com.vopros.bulkapedia.ui.components.bottomNavigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vopros.bulkapedia.storage.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    private val _config = MutableStateFlow(Pair("", false))
    val config = _config.asStateFlow()

    fun fetchConfig() {
        viewModelScope.launch { dataStore.config.collect { _config.emit(it) } }
    }

}