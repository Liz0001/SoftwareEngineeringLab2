package com.SoftwareEngineering.selab2.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoftwareEngineering.selab2.SpeechRecognition
import kotlinx.coroutines.delay

@Composable
fun SpeechToTextSection(
    vm: AppViewModel,
    speechRecognition: SpeechRecognition,
    ) {

    var recognizedText by remember { mutableStateOf("") }

    LaunchedEffect(recognizedText) {
        Log.i("TEXT", "Text from Speech: ${recognizedText}...................")
        delay(500)
    }

    Column {
        Button(
            onClick = {
                speechRecognition.startSpeechRecognition()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Speak!")
        }

        if (recognizedText.isNotEmpty()) {
            Text(
                text = recognizedText,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}