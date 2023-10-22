package com.vopros.bulkapedia.ui.screens.heroInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroType
import com.vopros.bulkapedia.ui.components.Difficult
import com.vopros.bulkapedia.ui.components.ScreenView
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.screens.hero.HeroThumbnail
import com.vopros.bulkapedia.ui.theme.BulkapediaTheme
import com.vopros.bulkapedia.utils.resourceManager

@Destination
@Composable
fun HeroInfoScreen(
    navigator: DestinationsNavigator,
    viewModel: HeroInfoViewModel,
    heroId: String
) {
    ScreenView(
        title = resourceManager.toSource(heroId),
        showBack = true,
        viewModel = viewModel,
        fetch = { fetchHero(heroId) }
    ) {

    }
}

val hero = Hero(
    id = "arnie",
    active = true,
    difficult = "easy",
    image = "https://firebasestorage.googleapis.com/v0/b/bulkapedia-66ef5.appspot.com/o/heroes%2Fshortguns%2Farnie.png?alt=media&token=9613b763-7d39-4262-a93c-f272783d55fd",
    type = HeroType.SHORTGUN,
    counterpicks = emptyList(),
    stats = emptyMap(),
    personalGears = emptyList()
)

@Preview
@Composable
fun HeroInfoScreen_Preview() {
    BulkapediaTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                HeroThumbnail(image = hero.image) {
                    Text(title = "Фракция: Skytech")
                }
            }
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(resource = R.string.difficult)
                    Difficult(mechanics = 1.5f, speed = 2f, attack = 3f)
                }
            }
        }
    }
}