package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import com.nvropotov.player.impl.R

@Composable
internal fun TitleBlock(track: MediaItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = Dimens.dp24)
    ) {
        Column(
            modifier = Modifier.weight(1f, fill = false)
        ) {
            Text(
                text = track.mediaMetadata.title.toString(),
                fontSize = Dimens.sp24,
                fontFamily = FontFamily(Font(R.font.golos_regular, weight = FontWeight(400))),
                maxLines = 1,
                color = Colors.textPrimary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = track.mediaMetadata.artist.toString(),
                fontSize = Dimens.sp16,
                fontFamily = FontFamily(Font(R.font.golos_regular, weight = FontWeight(400))),
                maxLines = 1,
                color = Colors.textPrimary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.width(Dimens.dp16))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_like_player),
            contentDescription = null,
            tint = Colors.accentColor,
            modifier = Modifier.size(Dimens.dp20)
        )
    }
}