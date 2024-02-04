package com.SoftwareEngineering.selab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.SoftwareEngineering.selab2.ui.App
import com.SoftwareEngineering.selab2.ui.screens.AppViewModel
import com.SoftwareEngineering.selab2.ui.theme.SELab2Theme


class MainActivity : ComponentActivity() {
    private lateinit var speechRecognition: SpeechRecognition

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val spokenText: String? =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { results ->
                        results[0]
                    }
                if (spokenText != null) {
                    if (spokenText.contains("open") && spokenText.contains("door")) viewModel.updateDatabase("doorOpen",true)
                    if (spokenText.contains("open") && spokenText.contains("window")) viewModel.updateDatabase("windowOpen",true)
                    if (spokenText.contains("on") && spokenText.contains("lamp")) viewModel.updateDatabase("lightOn",true)
                    if (spokenText.contains("close") && spokenText.contains("door")) viewModel.updateDatabase("doorOpen",false)
                    if (spokenText.contains("close") && spokenText.contains("window")) viewModel.updateDatabase("windowOpen",false)
                    if (spokenText.contains("off") && spokenText.contains("lamp")) viewModel.updateDatabase("lightOn",false)
                }
            }
        }
        speechRecognition = SpeechRecognition(this, startForResult)

        setContent {
            SELab2Theme {
                App(viewModel, speechRecognition)
            }
        }
    }
}
