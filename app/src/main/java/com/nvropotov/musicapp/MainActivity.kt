package com.nvropotov.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.nvropotov.musicapp.ui.theme.MusicAppTheme
import com.nvropotov.network.api.NetworkApi
import com.nvropotov.network.api.TrackDto
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkApi: NetworkApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MusicApplication).getComponent().inject(this)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                val tracks = remember { mutableStateOf(listOf<TrackDto>()) }
                LaunchedEffect(Unit) {
                    networkApi.provideTrackRepository().getTracks().onSuccess {
                        tracks.value = it
                    }
                }
                LazyColumn(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                    items(tracks.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(Color.Blue)
                        ) {
                            AsyncImage(
                                model = it.coverUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxHeight().aspectRatio(1f)
                            )
                            Column(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = it.title,
                                    color = Color.Black
                                )
                                Text(
                                    text = it.artist,
                                    color = Color.Black

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}