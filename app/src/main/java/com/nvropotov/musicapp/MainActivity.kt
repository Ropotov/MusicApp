package com.nvropotov.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.nvropotov.network.api.NetworkApi
import com.nvropotov.network.api.TrackRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkApi: NetworkApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MusicApplication).getComponent().inject(this)
        lifecycleScope.launch {
            val tracks = networkApi.provideTrackRepository().getTracks()
            Log.e("TAG", tracks.toString())
        }
        enableEdgeToEdge()
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}