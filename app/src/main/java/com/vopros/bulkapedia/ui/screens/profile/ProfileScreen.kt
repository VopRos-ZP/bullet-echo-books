package com.vopros.bulkapedia.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.ListContent
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.tab.Tab
import com.vopros.bulkapedia.ui.components.tab.TabRowWithPager
import com.vopros.bulkapedia.ui.components.userSet.UserSetCard

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
                .padding(20.dp)
        ) {
            ListContent(items = setsState.value) { sets ->
                TabRowWithPager(pages = tabs, content = sets) { listSet ->
                    ListContent(items = listSet) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            items(listSet) { HCenterBox { UserSetCard(it, true) } }
                        }
                    }
                }
            }
        }
    }
}