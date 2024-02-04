package com.SoftwareEngineering.selab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.SoftwareEngineering.selab2.ui.App
import com.SoftwareEngineering.selab2.ui.theme.SELab2Theme

class MainActivity : ComponentActivity() {
    private lateinit var speechRecognition: SpeechRecognition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->
            // Handle the result
        }
        speechRecognition = SpeechRecognition(this, startForResult)

        setContent {
            SELab2Theme {
                App(speechRecognition)
            }
        }
    }
}
