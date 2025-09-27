package com.nvropotov.musicapp.di

import com.nvropotov.network.api.NetworkApi
import com.nvropotov.network.impl.di.NetworkComponentHolder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun networkApi(): NetworkApi {
        NetworkComponentHolder.init()
        return NetworkComponentHolder.getApi()
    }
}