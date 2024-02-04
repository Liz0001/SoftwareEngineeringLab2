package com.SoftwareEngineering.selab2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.SoftwareEngineering.selab2.R
import com.SoftwareEngineering.selab2.VoiceToText
import com.SoftwareEngineering.selab2.data.VoiceToTextState

@Composable
fun SpeechToTextSection(
    vm: AppViewModel,
    state: VoiceToTextState,
    voiceToText: VoiceToText,
) {
    Column {
        Button(
            onClick = {
                if (state.isSpeaking) {
                    voiceToText.stopListening()
                } else {
                    voiceToText.startListening()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            if (state.isSpeaking) {
                Image(
                    painter = painterResource(R.drawable.stop),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
                Text("Speaking ...")

            } else {
                Image(
                    painter = painterResource(R.drawable.mic),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .padding(end = 8.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
                Text("Click to Speak")
            }
        }
        if (state.text.isNotEmpty()) {
            if (state.text.contains("open") && state.text.contains("door")) vm.updateDatabase("doorOpen",true)
            if (state.text.contains("open") && state.text.contains("window")) vm.updateDatabase("windowOpen",true)
            if (state.text.contains("on") && state.text.contains("lamp")) vm.updateDatabase("lightOn",true)
            if (state.text.contains("close") && state.text.contains("door")) vm.updateDatabase("doorOpen",false)
            if (state.text.contains("close") && state.text.contains("window")) vm.updateDatabase("windowOpen",false)
            if (state.text.contains("off") && state.text.contains("lamp")) vm.updateDatabase("lightOn",false)
        }
    }
}