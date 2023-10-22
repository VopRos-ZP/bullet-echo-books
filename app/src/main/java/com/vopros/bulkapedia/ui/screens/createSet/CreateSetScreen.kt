package com.vopros.bulkapedia.ui.screens.createSet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.gears.Gear
import com.vopros.bulkapedia.gears.GearCell
import com.vopros.bulkapedia.gears.GearSet
import com.vopros.bulkapedia.ui.components.BottomSheet
import com.vopros.bulkapedia.ui.components.Loading
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.button.OutlinedButton
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.components.userSet.GearImage
import com.vopros.bulkapedia.ui.components.userSet.Gears
import com.vopros.bulkapedia.ui.screens.hero.HeroThumbnail
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme
import com.vopros.bulkapedia.ui.theme.BulkapediaTheme
import com.vopros.bulkapedia.ui.theme.LocalBottomSheetState

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun CreateSetScreen(
    viewModel: CreateSetViewModel,
    heroId: String,
    setId: String?
) {
    val useCaseState = viewModel.useCase.collectAsState()
    val gears by viewModel.gears.collectAsState()
    var sheetItems = remember { mutableStateListOf<Any>() }
    ScreenView(
        title = R.string.create_set,
        showBack = true,
        viewModel = viewModel,
        fetch = { init(heroId, setId) }
    ) {
        when (val useCase = useCaseState.value) {
            null -> Loading()
            else -> {
                BottomSheet(sheetContent = {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 20.dp)
                    ) {
                        items(gears) {
                            SelectGearItem(gear = it)
                        }
                    }
                }, key = null, onClose = { viewModel.unselectGears() }) {
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
                                Gears(
                                    gears = useCase.set.gears,
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    viewModel.selectGear(it, heroId)
                                }
                            }
                        }
                    }
                    val sheetState = LocalBottomSheetState.current
                    LaunchedEffect(gears) {
                        when (gears) {
                            emptyList<Gear>() -> sheetState.hide()
                            else -> sheetState.show()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectGearItem_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark) {
            SelectGearItem(gear = Gear(
                id = "",
                gearCell = GearCell.HEAD,
                gearSet = GearSet.NONE,
                image = "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/gears%2Fdefault%2Fhead%2Fempty_head.png?alt=media&token=19388349-1aea-41de-af6c-5dfa9c344aaa"
            ))
        }
    }
}

@Composable
fun SelectGearItem(gear: Gear) {
    Card(radius = 10.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            GearImage(url = gear.image)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}