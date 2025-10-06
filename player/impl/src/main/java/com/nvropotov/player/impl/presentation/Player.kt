package com.nvropotov.player.impl.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalResources
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.nvropotov.player.impl.R
import com.nvropotov.player.impl.presentation.components.CollapsedPlayer
import com.nvropotov.player.impl.presentation.components.ExpandedPlayer

@Composable
fun Player(
    modifier: Modifier = Modifier, viewModel: PlayerViewModel
) {
    val resources = LocalResources.current

    val motionScene = remember {
        resources.openRawResource(R.raw.player_scene).bufferedReader().use { it.readText() }
    }

    MotionLayout(
        motionScene = MotionScene(motionScene),
        progress = progress,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        CollapsedPlayer(
            track = currentMediaItem,
            modifier = modifier.layoutId("collapsedContent"),
        )
        ExpandedPlayer(
            track = currentMediaItem,
            position = currentPosition,
            modifier = modifier.layoutId("expandedContent"),
        )
    }
}