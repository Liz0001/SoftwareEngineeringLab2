package com.SoftwareEngineering.selab2.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpeechToText() {
    Row(
        modifier = Modifier
            .padding(top = 20.dp),
    ) {


        Button(
            onClick = {}
        ) {
            Text(text = "Speak")
        }
    }
}