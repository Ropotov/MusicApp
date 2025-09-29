package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun Indicator(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoRipple(onClick)
            .padding(horizontal = Dimens.dp24)
            .padding(vertical = Dimens.dp12)
    ) {
        Spacer(
            modifier = Modifier
                .height(Dimens.dp4)
                .width(Dimens.dp120)
                .align(Alignment.Center)
                .background(Colors.accentColor)
                .clip(RoundedCornerShape(Dimens.dp2))
        )
    }
}

@Preview
@Composable
private fun PreviewIndicator() {
    Indicator { }
}
