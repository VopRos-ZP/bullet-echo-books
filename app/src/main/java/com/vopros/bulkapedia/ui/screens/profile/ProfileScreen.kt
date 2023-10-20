package com.vopros.bulkapedia.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.CenterBox
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Loading
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.tab.Tab
import com.vopros.bulkapedia.ui.components.tab.TabRowWithPager
import com.vopros.bulkapedia.ui.components.userSet.UserSetCard
import com.vopros.bulkapedia.userSet.UserSetUseCase

@Destination
@Composable
fun ProfileScreen(viewModel: ProfileViewModel, userId: String) {
    val userState = viewModel.user.collectAsState()
    val setsState = viewModel.sets.collectAsState()

    val profile = stringResource(R.string.empty)
    var title by remember { mutableStateOf(profile) }

    val tabs = listOf(Tab(R.string.your_sets), Tab(R.string.favorites))
    ScreenView(
        title = title,
        viewModel = viewModel,
        fetch = { fetchData(userId) }
    ) {
        LaunchedEffect(userState.value) {
            if (userState.value != null) {
                title = userState.value!!.nickname
            }
        }
        // mains
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            when (val sets = setsState.value) {
                null -> Loading()
                emptyList<List<UserSetUseCase>>() -> CenterBox { Text(R.string.empty_sets) }
                else -> {
                    TabRowWithPager(pages = tabs, content = sets) { listSet ->
                        when (listSet) {
                            emptyList<UserSetUseCase>() -> CenterBox { Text(R.string.empty_sets) }
                            else -> LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                items(listSet) { HCenterBox { UserSetCard(it, true) } }
                            }
                        }
                    }
                }
            }
        }
    }
}