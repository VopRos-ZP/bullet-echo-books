package com.vopros.bulkapedia.ui.screens.createSet

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.BottomSheet
import com.vopros.bulkapedia.ui.components.Loading
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.button.OutlinedButton
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.components.userSet.Gears
import com.vopros.bulkapedia.ui.screens.hero.HeroThumbnail

@Destination
@Composable
fun CreateSetScreen(
    viewModel: CreateSetViewModel,
    heroId: String,
    setId: String?
) {
    val useCaseState = viewModel.useCase.collectAsState()
    ScreenView(
        title = R.string.create_set,
        showBack = true,
        viewModel = viewModel,
        fetch = { init(heroId, setId) }
    ) {
        when (val useCase = useCaseState.value) {
            null -> Loading()
            else -> {
                BottomSheet(sheetContent = { /*TODO*/ }, key = null, onClose = { /*TODO*/ }) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(20.dp)
                    ) {
                        item {
                            HeroThumbnail(image = useCase.hero.image) {
                                OutlinedButton(
                                    onClick = { viewModel.saveSet(useCase.set) },
                                    text = R.string.save
                                )
                            }
                        }
                        item {
                            Card {
                                Gears(gears = useCase.set.gears, modifier = Modifier.fillMaxWidth()) {
                                    Log.d("CreateSet", it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}