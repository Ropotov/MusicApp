package com.nvropotov.musicapp

import android.app.Application
import com.nvropotov.musicapp.di.AppComponent
import com.nvropotov.musicapp.di.DaggerAppComponent

class MusicApplication : Application() {
    private var component: AppComponent? = null
    fun getComponent() = checkNotNull(component)
    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
        checkNotNull(component).inject(this)
    }
}