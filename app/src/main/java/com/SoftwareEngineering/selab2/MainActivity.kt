package com.SoftwareEngineering.selab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.Manifest
import androidx.compose.runtime.collectAsState
import com.SoftwareEngineering.selab2.ui.App
import com.SoftwareEngineering.selab2.ui.theme.SELab2Theme

class MainActivity : ComponentActivity() {
    private val voiceToText by lazy { VoiceToText(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var canRecord by remember { mutableStateOf(false) }
            val state by voiceToText.state.collectAsState()

            val recordAudioLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    canRecord = isGranted
                }
            )

            LaunchedEffect(
                key1 = recordAudioLauncher,
                block = {
                    recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            )

            SELab2Theme {
                App(state, voiceToText)
            }
        }
    }
}
