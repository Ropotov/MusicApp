package com.nvropotov.player.impl.presentation

import android.content.ComponentName
import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.nvropotov.network.api.TrackDto
import javax.inject.Inject

class PlaybackControlImpl @Inject constructor(context: Context) : PlaybackControl {

    private var mediaControllerFuture: ListenableFuture<MediaController>
    private val mediaController: MediaController?
        get() = runCatching {
            if (mediaControllerFuture.isDone) mediaControllerFuture.get() else null
        }.getOrNull()

    override var mediaControllerCallback: ((
        PlayerState,
        MediaItem?,
        Long,
        Long,
        Boolean,
        Boolean,
    ) -> Unit)? = null

    init {
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        mediaControllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        mediaControllerFuture.addListener({ controllerListener() }, MoreExecutors.directExecutor())
    }

    override fun addMediaItems(tracks: List<TrackDto>) {
        val mediaItems = tracks.map {
            MediaItem.Builder()
                .setMediaId(it.audioUrl)
                .setUri(it.audioUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setTitle(it.title)
                        .setArtist(it.artist)
                        .setArtworkUri(it.coverUrl.toUri())
                        .build()
                )
                .build()
        }

        mediaController?.setMediaItems(mediaItems)
    }

    override fun play(mediaItemIndex: Int) {
        mediaController?.apply {
            seekToDefaultPosition(mediaItemIndex)
            playWhenReady = true
            prepare()
        }
    }

    override fun resume() {
        mediaController?.play()
    }

    override fun pause() {
        mediaController?.pause()
    }

    override fun getCurrentPosition(): Long {
        return mediaController?.currentPosition ?: 0L
    }

    override fun destroy() {
        MediaController.releaseFuture(mediaControllerFuture)
    }

    override fun skipToNextSong() {
        mediaController?.seekToNext()
    }

    override fun skipToPreviousSong() {
        mediaController?.seekToPrevious()
    }

    override fun getCurrentSong(): MediaItem? {
        return mediaController?.currentMediaItem
    }

    override fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }

    private fun controllerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)

                with(player) {
                    mediaControllerCallback?.invoke(
                        playbackState.toPlayerState(isPlaying),
                        currentMediaItem,
                        currentPosition.coerceAtLeast(0L),
                        duration.coerceAtLeast(0L),
                        shuffleModeEnabled,
                        repeatMode == Player.REPEAT_MODE_ONE
                    )
                }
            }
        })
    }

    private fun Int.toPlayerState(isPlaying: Boolean) =
        when (this) {
            Player.STATE_IDLE -> PlayerState.STOPPED
            Player.STATE_ENDED -> PlayerState.STOPPED
            else -> if (isPlaying) PlayerState.PLAYING else PlayerState.PAUSED
        }
}