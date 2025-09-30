package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.nvropotov.player.impl.R

@Composable
internal fun PlayButton(isPlaying: Boolean) {

    val icon = if (isPlaying) {
        R.drawable.ic_pause
    } else {
        R.drawable.ic_play
    }

    Box(
        modifier = Modifier
            .size(Dimens.dp70)
            .clip(CircleShape)
            .background(Colors.playOrPauseShape)
    ) {
        Spacer(
            modifier = Modifier
                .size(Dimens.dp64)
                .clip(CircleShape)
                .background(Colors.playOrPause)
                .align(Alignment.CenterEnd)
                .innerShadow(CircleShape) {
                    radius = Dimens.radius40
                    offset = Offset(Dimens.radius20, Dimens.radius20)
                    color = Colors.playOrPauseShadow
                }
        )
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(Dimens.dp16)
                .align(Alignment.Center),
            tint = Colors.icPlayOrPause
        )
    }
}

@Preview
@Composable
private fun PreviewPlayButton() {
    PlayButton(false)
}