package com.nvropotov.network.api

interface TrackRepository {
    suspend fun getTracks(): Result<List<TrackDto>>
}