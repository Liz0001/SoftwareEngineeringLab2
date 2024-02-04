package com.SoftwareEngineering.selab2

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import java.util.Locale

class SpeechRecognition(private val context: Context, private val startForResult: ActivityResultLauncher<Intent>) {

    private fun speechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Toast.makeText(context, "Speech Not Recognized!",Toast.LENGTH_SHORT).show()
            return
        } else {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now")
            }
            startForResult.launch(intent)
        }
    }

    fun startSpeechRecognition() {
        speechInput()
    }
}