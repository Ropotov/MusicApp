package com.nvropotov.network.impl.data

import com.google.firebase.firestore.FirebaseFirestore
import com.nvropotov.network.api.TrackDto
import com.nvropotov.network.api.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val apiService: YandexApiService,
    private val firestore: FirebaseFirestore,
) : TrackRepository {

    override suspend fun getTracks(): Result<List<TrackDto>> = runCatching {
        val snapshot = firestore.collection(NAME_COLLECTIONS).get().await()
        val tracks = snapshot.documents.mapNotNull { it.toObject(TrackDto::class.java) }
        val directLinkCache = mutableMapOf<String, String>()
        coroutineScope {
            tracks.map { track ->
                async(Dispatchers.IO) {
                    val audioDirectLink = directLinkCache.getOrPut(track.audioUrl) {
                        apiService.getDirectLink(track.audioUrl).href ?: String()
                    }
                    val coverDirectLink = directLinkCache.getOrPut(track.coverUrl) {
                        apiService.getDirectLink(track.coverUrl).href ?: String()
                    }
                    track.copy(audioUrl = audioDirectLink, coverUrl = coverDirectLink)
                }
            }.awaitAll()
        }
    }

    companion object {
        private const val NAME_COLLECTIONS = "tracks"
    }
}