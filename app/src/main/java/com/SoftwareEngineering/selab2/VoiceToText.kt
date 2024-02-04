package com.SoftwareEngineering.selab2

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.SoftwareEngineering.selab2.data.VoiceToTextState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VoiceToText(
    private val app: Application
): RecognitionListener {

    private val _state  = MutableStateFlow(VoiceToTextState())
    val state = _state.asStateFlow()

    val recognizer = SpeechRecognizer.createSpeechRecognizer(app)


    fun startListening(langCode: String = "en") {
        _state.update { VoiceToTextState() }

        if (!SpeechRecognizer.isRecognitionAvailable(app)) {
            _state.update {
                it.copy(
                    error = "Sorry, voice recognition is not available!"
                )
            }
        }
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, langCode)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now")
        }
        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)

        _state.update {
            it.copy(
                isSpeaking = true
            )
        }
    }

    fun stopListening() {
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        recognizer.stopListening()
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onBeginningOfSpeech() = Unit
    override fun onRmsChanged(p0: Float) = Unit
    override fun onBufferReceived(p0: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT) {
            return
        }
        _state.update {
            it.copy(
                error = "Error: $error"
            )
        }

    }

    override fun onResults(results: Bundle?) {
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let { res ->
                _state.update {
                    it.copy(
                        text = res
                    )
                }
            }
    }

    override fun onPartialResults(p0: Bundle?) = Unit
    override fun onEvent(p0: Int, p1: Bundle?) = Unit
}
