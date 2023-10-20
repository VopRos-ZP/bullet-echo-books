package com.vopros.bulkapedia.ui.screens.profile

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfileScreen(viewModel: ProfileViewModel, userId: String) {
//    val profile = stringResource(R.string.profile)
//    var title by remember { mutableStateOf(profile) }
//    Screen<Pair<User, List<UserSetUseCase>>, ProfileViewModel>(
//        title = title,
//        fetch = { startIntent(ProfileViewIntent.Start(userId)) },
//        dispose = { startIntent(ProfileViewIntent.Dispose) }
//    ) { _, (user, sets) ->
//        LaunchedEffect(user) { title = user.nickname }
//        // mains
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 20.dp)
//        ) {
//            when (sets) {
//                emptyList<UserSetUseCase>() -> CenterBox { Text(R.string.empty_sets) }
//                else -> {
//                    LazyColumn(
//                        verticalArrangement = Arrangement.spacedBy(5.dp)
//                    ) {
//                        items(sets) {
//                            HCenterBox { UserSetCard(it, true) }
//                        }
//                    }
//                }
//            }
//        }
//    }
}