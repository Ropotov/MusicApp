package com.nvropotov.player.impl.presentation

import androidx.media3.common.MediaItem
import com.nvropotov.network.api.TrackDto

interface PlaybackControl {
    var mediaControllerCallback: (
        (
        playerState: PlayerState,
        currentMusic: MediaItem?,
        currentPosition: Long,
        totalDuration: Long,
        isShuffleEnabled: Boolean,
        isRepeatOneEnabled: Boolean
    ) -> Unit
    )?

    fun addMediaItems(tracks: List<TrackDto>)

    fun play(mediaItemIndex: Int)

    fun resume()

    fun pause()

    fun getCurrentPosition(): Long

    fun destroy()

    fun skipToNextSong()

    fun skipToPreviousSong()

    fun getCurrentSong(): MediaItem?

    fun seekTo(position: Long)
}