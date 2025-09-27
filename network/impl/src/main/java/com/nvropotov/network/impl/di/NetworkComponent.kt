package com.nvropotov.network.impl.di

import com.nvropotov.network.api.NetworkApi
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkApi