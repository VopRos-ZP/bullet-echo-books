package com.vopros.bulkapedia.gears

import androidx.compose.ui.graphics.Color
import com.vopros.bulkapedia.ui.theme.Common
import com.vopros.bulkapedia.ui.theme.Rare
import com.vopros.bulkapedia.ui.theme.Epic
import com.vopros.bulkapedia.ui.theme.Legendary
import com.vopros.bulkapedia.ui.theme.Mythic
import com.vopros.bulkapedia.ui.theme.Supreme
import com.vopros.bulkapedia.ui.theme.Ultimate
import com.vopros.bulkapedia.ui.theme.Celestial
import com.vopros.bulkapedia.ui.theme.Stellar
import com.vopros.bulkapedia.ui.theme.Immortal
import com.vopros.bulkapedia.ui.theme.Divine

enum class Rank(val color: Color) {
    COMMON(Common),
    RARE(Rare),
    EPIC(Epic),
    LEGENDARY(Legendary),
    MYTHIC(Mythic),
    SUPREME(Supreme),
    ULTIMATE(Ultimate),
    CELESTIAL(Celestial),
    STELLAR(Stellar),
    IMMORTAL(Immortal),
    DIVINE(Divine);
}