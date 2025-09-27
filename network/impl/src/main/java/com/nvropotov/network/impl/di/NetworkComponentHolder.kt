package com.nvropotov.network.impl.di

import com.nvropotov.network.api.NetworkApi

object NetworkComponentHolder {

    @Volatile
    private var component: NetworkComponent? = null

    fun getApi(): NetworkApi = checkNotNull(component)

    fun init() {
        synchronized(this){
            if (component == null) {
                component = DaggerNetworkComponent.builder().build()
            }
        }
    }
}
