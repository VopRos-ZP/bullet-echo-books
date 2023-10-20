package com.vopros.bulkapedia.ui.components.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.vopros.bulkapedia.ui.components.CustomIndicator
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.ListContent
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> TabRowWithPager(
    pages: List<Tab>,
    content: List<T>?,
    pattern: @Composable (T) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pager = rememberPagerState { pages.size }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(15.dp),
            backgroundColor = BulkaPediaTheme.colors.primaryDark,
        ) {
            androidx.compose.material.TabRow(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                selectedTabIndex = pager.currentPage,
                backgroundColor = BulkaPediaTheme.colors.primary,
                indicator = { CustomIndicator(tabPositions = it, pagerState = pager) }
            ) {
                List(pages.size) { i ->
                    Tab(
                        modifier = Modifier
                            .zIndex(2f)
                            .padding(vertical = 10.dp),
                        selected = pager.currentPage == i,
                        onClick = { scope.launch { pager.animateScrollToPage(i) } },
                    ) {
                        Text(
                            resource = pages[i].title,
                            color = if (pager.currentPage == i) BulkaPediaTheme.colors.primaryDark
                            else BulkaPediaTheme.colors.tintColor
                        )
                    }
                }
            }
        }
        ListContent(items = content, sizing = {
            HCenterBox(modifier = Modifier
                .padding(horizontal = 20.dp)
                .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                .height((25 * 2 + 75 * 3).dp)
            ) { it() }
        }) { items -> HorizontalPager(state = pager, pageSpacing = 20.dp) { pattern(items[it]) } }
    }
}