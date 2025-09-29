package com.nvropotov.player.impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.nvropotov.player.impl.R

@Composable
internal fun PlayerProgressText(
    position: State<Long>,
    duration: Long,
) {
    val remainingMs = (duration - position.value).coerceAtLeast(0L)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp24),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formatTime(position.value),
            fontSize = Dimens.sp10,
            color = Colors.accentColor,
            fontFamily = FontFamily(Font(R.font.golos_regular, weight = FontWeight(400))),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = formatTime(remainingMs),
            fontSize = Dimens.sp10,
            color = Colors.accentColor,
            fontFamily = FontFamily(Font(R.font.golos_regular, weight = FontWeight(400))),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}