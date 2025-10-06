package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.MediaItem
import coil.compose.AsyncImage
import com.nvropotov.player.impl.R

@Composable
internal fun CollapsedPlayer(
    modifier: Modifier = Modifier,
    track: MediaItem,
    onExpanded: () -> Unit,
    onTogglePlay: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(Dimens.dp64)
            .background(Colors.miniPlayerBg)
            .padding(horizontal = Dimens.dp24)
            .clickableNoRipple(onExpanded)
    ) {
        AsyncImage(
            model = track.mediaMetadata.artworkUri,
            contentDescription = null,
            modifier = Modifier.size(Dimens.dp48)
        )
        Spacer(Modifier.width(Dimens.dp16))
        Text(
            text = track.mediaMetadata.title.toString(),
            fontSize = Dimens.sp16,
            fontFamily = FontFamily(Font(R.font.golos_regular, weight = FontWeight(400))),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(Dimens.dp4))
        Image(
            painter = painterResource(R.drawable.ic_mini_player_play),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(Dimens.dp40)
                .clickableNoRipple(onTogglePlay),
        )
        Spacer(Modifier.width(Dimens.dp8))
        Image(
            painter = painterResource(R.drawable.ic_mini_player_next),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(Dimens.dp40)
                .clickableNoRipple(onNext),
        )
    }
}

@Preview
@Composable
private fun PreviewCollapsedPlayer() {
    CollapsedPlayer(
        track = MediaItem.EMPTY,
        onExpanded = {},
        onNext = {},
        onTogglePlay = {}
    )
}