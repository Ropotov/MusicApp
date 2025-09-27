package com.nvropotov.musicapp.di

import com.nvropotov.musicapp.MainActivity
import com.nvropotov.musicapp.MusicApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(application: MusicApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}