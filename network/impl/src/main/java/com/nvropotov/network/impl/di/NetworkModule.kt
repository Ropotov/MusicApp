package com.nvropotov.network.impl.di

import com.google.firebase.firestore.FirebaseFirestore
import com.nvropotov.network.api.TrackRepository
import com.nvropotov.network.impl.data.TracksRepositoryImpl
import com.nvropotov.network.impl.data.YandexApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [NetworkModule.Bind::class])
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(YandexApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideYandexApiService(
        retrofit: Retrofit,
    ): YandexApiService =
        retrofit.create(YandexApiService::class.java)

    @Provides
    fun provideFireStore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

    @Module
    interface Bind {
        @Binds
        fun bindsTrackRepository(tracksRepositoryImpl: TracksRepositoryImpl): TrackRepository
    }
}