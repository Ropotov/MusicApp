package com.nvropotov.network.impl.data

import com.google.firebase.firestore.FirebaseFirestore
import com.nvropotov.network.api.TrackDto
import com.nvropotov.network.api.TrackRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val apiService: YandexApiService,
    private val firestore: FirebaseFirestore,
): TrackRepository {

    override suspend fun getTracks(): List<TrackDto>  = coroutineScope {
        val snapshot = firestore.collection(NAME_COLLECTIONS).get().await()
        val tracks = snapshot.documents.mapNotNull { it.toObject(TrackDto::class.java) }
        val tracksWithDirectLinks = tracks.map { track ->
            async {
                val directLink = apiService.getDirectLink(track.audioUrl).href ?: String()
                track.copy(audioUrl = directLink)
            }
        }
        tracksWithDirectLinks.awaitAll()
    }

    companion object {
        private const val NAME_COLLECTIONS = "tracks"
    }
}