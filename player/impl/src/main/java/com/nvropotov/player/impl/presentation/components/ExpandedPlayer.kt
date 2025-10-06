package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.MediaItem

@Composable
internal fun ExpandedPlayer(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    track: MediaItem,
    position: Long,
    onCollapse: () -> Unit,
    onSeek: (Long) -> Unit,
    onTogglePlay: () -> Unit,
    onPrev: () -> Unit,
    onNext: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Colors.playerBg)
    ) {
        Indicator(onClick = onCollapse)
        Spacer(modifier = modifier.weight(1f))
        PlayerCover(track.mediaMetadata.artworkUri)
        Spacer(modifier = modifier.weight(1f))
        TitleBlock(track)
        Spacer(modifier = modifier.height(Dimens.dp18))
        PlayerProgressBar(
            position = rememberUpdatedState(position),
            duration = track.mediaMetadata.durationMs ?: 0L,
            onSeek = onSeek
        )
        Spacer(modifier = Modifier.height(Dimens.dp24))
        ControlPanel(
            isPlaying = isPlaying,
            onTogglePlay = onTogglePlay,
            onPrev = onPrev,
            onNext = onNext
        )
        Spacer(modifier = modifier.weight(1f))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewExpandedPlayer() {
    ExpandedPlayer(
        isPlaying = false,
        track = MediaItem.EMPTY,
        position = 1L,
        onCollapse = {},
        onSeek = {},
        onTogglePlay = {},
        onNext = {},
        onPrev = {},
    )
}
