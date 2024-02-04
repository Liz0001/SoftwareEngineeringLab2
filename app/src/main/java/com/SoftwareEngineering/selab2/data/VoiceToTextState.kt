package com.SoftwareEngineering.selab2.data

data class VoiceToTextState(
    val text: String = "",
    val isSpeaking: Boolean = false,
    val error: String? = null
)
