package com.nvropotov.network.impl.data

import com.nvropotov.network.impl.data.YandexResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YandexApiService {

    @GET("v1/disk/public/resources/download")
    suspend fun getDirectLink(
        @Query("public_key") link: String,
    ): YandexResponse

    companion object {
        const val BASE_URL = "https://cloud-api.yandex.net/"
    }
}