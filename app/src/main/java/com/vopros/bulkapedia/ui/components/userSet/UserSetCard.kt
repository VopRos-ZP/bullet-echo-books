package com.vopros.bulkapedia.ui.components.userSet

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.bulkapedia.ui.components.IconButton
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.OutlinedIconButton
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.cards.Card
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetUseCase

@Composable
fun UserSetCard(
    container: UserSetUseCase,
    withHeroIcon: Boolean = false,
    onCommentClick: () -> Unit = {}
) {
    var expand by remember { mutableStateOf(false) }
    val viewModel: UserSetCardViewModel = hiltViewModel()
    LaunchedEffect(container.set) { viewModel.fetchConfig() }
    val config by viewModel.config.collectAsState()
    UserSetCard(container.set) {
        Crossfade(
            modifier = Modifier.fillMaxWidth().weight(1f),
            targetState = expand, label = ""
        ) {
            when (it && withHeroIcon) {
                true -> Box(modifier = Modifier.fillMaxSize()) {
                    Image(url = container.hero.image, modifier = Modifier.fillMaxSize())
                }
                else -> Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(title = container.user.nickname)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(title = "${container.set.liked.size}")
                        IconButton(
                            onClick = { viewModel.like(container) },
                            tint = Color.Red,
                            icon = if (container.set.liked.contains(config?.first)) Icons.Default.Favorite
                            else Icons.Default.FavoriteBorder
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = when (withHeroIcon) {
                true -> Arrangement.SpaceBetween
                else -> Arrangement.SpaceEvenly
            },
        ) {
            OutlinedIconButton(onClick = { /* settings click */ }, icon = Icons.Default.Settings)
            OutlinedIconButton(onClick = onCommentClick, icon = Icons.Default.Comment)
            if (withHeroIcon) {
                OutlinedIconButton(
                    onClick = { expand = !expand },
                    icon = if (expand) Icons.Default.HideImage else Icons.Default.Image
                )
            }
        }
    }
}

@Composable
fun UserSetCard(
    userSet: UserSet,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(radius = 15.dp) {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Gears(gears = userSet.gears)
            Column(
                Modifier.height((75 * 3).dp + 20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
        }
    }
}

@Composable
fun Gears(gears: Map<String, String>, modifier: Modifier = Modifier, onClick: (String) -> Unit = {}) {
    Column(
        modifier = modifier.height((75 * 3).dp + 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GearRow(gears = gears, listOf("head", "body"), onClick)
        GearRow(gears = gears, listOf("arm", "leg"), onClick)
        GearRow(gears = gears, listOf("decor", "device"), onClick)
    }
}

@Composable
private fun GearRow(gears: Map<String, String>, keys: List<String>, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier.width((75 * 2).dp + 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        gears.filter { keys.contains(it.key) }.map { (key, value) ->
            Image(
                url = value,
                modifier = Modifier
                    .size(75.dp)
                    .background(BulkaPediaTheme.colors.primaryDark, RoundedCornerShape(15.dp))
                    .border(2.dp, BulkaPediaTheme.colors.tintColor, RoundedCornerShape(15.dp))
                    .clickable { onClick(key) }
            )
        }
    }
}