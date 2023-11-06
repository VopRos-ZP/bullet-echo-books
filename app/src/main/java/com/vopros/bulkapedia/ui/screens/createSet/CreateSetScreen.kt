package com.vopros.bulkapedia.ui.screens.createSet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.effect.Effect
import com.vopros.bulkapedia.firebase.getRankEffects
import com.vopros.bulkapedia.gears.Gear
import com.vopros.bulkapedia.gears.Rank
import com.vopros.bulkapedia.ui.components.BottomSheet
import com.vopros.bulkapedia.ui.components.Loading
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.button.OutlinedButton
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.components.userSet.GearImage
import com.vopros.bulkapedia.ui.components.userSet.Gears
import com.vopros.bulkapedia.ui.screens.hero.HeroThumbnail
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme
import com.vopros.bulkapedia.ui.theme.BulkapediaTheme
import com.vopros.bulkapedia.ui.theme.LocalBottomSheetState
import com.vopros.bulkapedia.utils.ResourceManager
import com.vopros.bulkapedia.utils.resourceManager

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
    var selectedGear by remember(gears) { mutableStateOf<Gear?>(null) }
    val rankEffects by remember(selectedGear) { mutableStateOf(selectedGear?.getRankEffects() ?: emptyMap()) }
    var selectedEffects by remember(rankEffects) { mutableStateOf(emptyList<Effect>()) }
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
                        if (selectedGear == null) {
                            items(gears) {
                                SelectGearItem(gear = it) {
                                    selectedGear = it
                                }
                            }
                        } else {
                            rankEffects.map { (rank, effs) ->
                                item {
                                    SelectRankItem(rank, effs) {
                                        selectedEffects = effs

                                    }
                                }
                            }
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
fun SelectRankItem_Preview() {
    resourceManager = ResourceManager()
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark) {
            SelectRankItem(rank = Rank.SUPREME, effects = listOf(
                Effect(6, true, "speed_effect"),
                Effect(6, true, "speed_effect"),
            ))
        }
    }
}

@Composable
fun SelectRankItem(rank: Rank, effects: List<Effect>, onClick: () -> Unit = {}) {
    SelectGearItem(onClick) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, rank.color, RoundedCornerShape(15.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            effects.map { e ->
                val sign = if (e.number >= 0) "+" else "-"
                val description = stringResource(id = resourceManager.toSource(e.description))
                Text(
                    title = "$sign${e.number}${if (e.percent) "%" else ""} $description",
                    color = rank.color,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun SelectGearItem(gear: Gear, onClick: () -> Unit = {}) {
    SelectGearItem(onClick) {
        GearImage(url = gear.image)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun SelectGearItem(onClick: () -> Unit = {}, content: @Composable RowScope.() -> Unit) {
    Card(radius = 10.dp, onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            content = content
        )
    }
}