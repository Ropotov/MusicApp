package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.nvropotov.player.impl.R

@Composable
internal fun ControlPanel(isPlaying: Boolean) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = Dimens.dp24)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_shuffle),
            modifier = Modifier.size(Dimens.dp24),
            contentDescription = null,
            tint = Colors.accentColor
        )
        ControlButton(R.drawable.ic_prev)
        PlayButton(isPlaying)
        ControlButton(R.drawable.ic_next)
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_download),
            modifier = Modifier.size(Dimens.dp24),
            contentDescription = null,
            tint = Colors.accentColor
        )
    }
}

@Preview
@Composable
private fun PreviewControlPanel() {
    ControlPanel(false)
}