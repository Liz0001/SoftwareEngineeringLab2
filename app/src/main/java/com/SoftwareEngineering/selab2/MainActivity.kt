package com.SoftwareEngineering.selab2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.SoftwareEngineering.selab2.ui.App
import com.SoftwareEngineering.selab2.ui.theme.SELab2Theme

class MainActivity : ComponentActivity() {
    private lateinit var speechRecognition: SpeechRecognition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val spokenText: String? =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { results ->
                        results[0]
                    }

                Log.i("spokenText", "$spokenText")
            }
        }

        speechRecognition = SpeechRecognition(this, startForResult)

        setContent {
            SELab2Theme {
                App(speechRecognition)
            }
        }
    }
}
