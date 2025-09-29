package com.nvropotov.player.impl.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
internal fun ControlButton(
    @DrawableRes icon: Int,
) {
    Box(
        modifier = Modifier
            .size(Dimens.dp70)
            .clip(CircleShape)
            .background(Colors.nextOrPrevShape)
    ) {
        Spacer(
            modifier = Modifier
                .size(Dimens.dp64)
                .clip(CircleShape)
                .background(Colors.nextOrPrev)
                .align(Alignment.CenterStart)
                .innerShadow(CircleShape) {
                    radius = Dimens.radius20
                    offset = Offset(Dimens.radius20, Dimens.radius20)
                    color = Colors.nextOrPrevShadow
                }
        )
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Colors.icNextOrPrev,
            modifier = Modifier
                .align(Alignment.Center)
                .width(Dimens.dp24),
        )
    }
}

@Preview
@Composable
private fun PreviewControlButton() {
    ControlButton(R.drawable.ic_next)
}