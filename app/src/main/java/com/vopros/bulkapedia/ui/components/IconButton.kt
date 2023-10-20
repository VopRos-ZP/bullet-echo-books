package com.vopros.bulkapedia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = BulkaPediaTheme.colors.tintColor,
    icon: ImageVector
) {
    androidx.compose.material.IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint
        )
    }
}

@Composable
fun OutlinedIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = BulkaPediaTheme.colors.tintColor,
    icon: ImageVector
) {
    androidx.compose.material.IconButton(
        onClick = onClick,
        modifier = modifier
            .size(40.dp)
            .background(Color.Transparent, RoundedCornerShape(10.dp))
            .border(1.dp, BulkaPediaTheme.colors.tintColor, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(5.dp),
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint
        )
    }
}