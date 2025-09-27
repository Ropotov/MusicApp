package com.nvropotov.network.api

interface TrackRepository {
    suspend fun getTracks(): List<TrackDto>
}