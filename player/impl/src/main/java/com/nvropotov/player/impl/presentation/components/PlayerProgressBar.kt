package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.nvropotov.player.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlayerProgressBar(
    position: State<Long>,
    duration: Long,
    onSeek: (Long) -> Unit,
) {
    val progress = if (duration > 0) position.value.toFloat() / duration else 0f

    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.dp8)
    ) {
        Slider(
            value = progress.coerceIn(0f, 1f),
            onValueChange = { newValue -> onSeek((newValue * duration).toLong()) },
            thumb = {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_thumb),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.dp20)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp14)
                .height(Dimens.dp6),
            track = { sliderState ->
                val colors = SliderDefaults.colors(
                    thumbColor = Colors.accentColor,
                    activeTrackColor = Colors.accentColor,
                    inactiveTrackColor = Colors.inactiveSeekBarColor,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                )
                val trackHeight = Dimens.dp6
                val trackHeightPx = with(LocalDensity.current) { trackHeight.toPx() }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.dp6)
                        .clip(RoundedCornerShape(Dimens.dp6))
                        .drawBehind {
                            val trackWidth = size.width
                            val y = size.height / 2 - trackHeightPx / 2
                            val thumbCenterX = trackWidth * sliderState.value
                            drawRect(
                                color = colors.activeTrackColor,
                                topLeft = Offset(0f, y),
                                size = Size(thumbCenterX, trackHeightPx)
                            )
                            drawRect(
                                color = colors.inactiveTrackColor,
                                topLeft = Offset(thumbCenterX, y),
                                size = Size(trackWidth - thumbCenterX, trackHeightPx)
                            )
                        }
                )
            }
        )
        PlayerProgressText(position, duration)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewPlayerProgressBar() {
    PlayerProgressBar(
        position = rememberUpdatedState(newValue = 8L),
        duration = 12L
    ) { }
}