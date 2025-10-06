package com.nvropotov.player.impl.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import coil.compose.AsyncImage

@Composable
internal fun PlayerCover(cover: Uri? = String().toUri()) {
    AsyncImage(
        model = cover,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp24)
            .clip(RoundedCornerShape(Dimens.dp44))
            .aspectRatio(1f)
    )
}

@Preview
@Composable
private fun PreviewPlayerCover() {
    PlayerCover(String().toUri())
}