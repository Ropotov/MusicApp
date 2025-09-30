package com.nvropotov.network.api

data class TrackDto(
    val artist: String = String(),
    val title: String = String(),
    val coverUrl: String = String(),
    val audioUrl: String = String(),
)