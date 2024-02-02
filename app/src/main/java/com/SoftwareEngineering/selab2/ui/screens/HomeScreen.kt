package com.SoftwareEngineering.selab2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoftwareEngineering.selab2.R
import com.SoftwareEngineering.selab2.SpeechRecognition
import com.google.firebase.database.FirebaseDatabase



@Composable
fun HomeScreen(speechRecognition: SpeechRecognition) {
    val database = FirebaseDatabase.getInstance()
    val statesRef = database.getReference("states")

    var lightOn by remember { mutableStateOf(false) }
    var doorOpen by remember { mutableStateOf(false) }
    var windowOpen by remember { mutableStateOf(false) }

    // Fetch and listen for real-time updates
    LaunchedEffect(key1 = Unit) {
//        fetch initial state
        statesRef.child("lightOn").get().addOnSuccessListener { snapshot ->
            lightOn = snapshot.getValue(Boolean::class.java) ?: false
        }
        statesRef.child("doorOpen").get().addOnSuccessListener { snapshot ->
            doorOpen = snapshot.getValue(Boolean::class.java) ?: false
        }
        statesRef.child("windowOpen").get().addOnSuccessListener { snapshot ->
            windowOpen = snapshot.getValue(Boolean::class.java) ?: false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        DeviceSwitch("LAMP", lightOn) { newState ->
            lightOn = newState
            statesRef.child("lightOn").setValue(newState)
        }
        DeviceSwitch("DOOR", doorOpen) { newState ->
            doorOpen = newState
            statesRef.child("doorOpen").setValue(newState)
        }
        DeviceSwitch("WINDOW", windowOpen) { newState ->
            windowOpen = newState
            statesRef.child("windowOpen").setValue(newState)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SpeechRecognitionSection(speechRecognition)
        }
    }
}

@Composable
fun DeviceSwitch(name: String, state: Boolean, onToggle: (Boolean) -> Unit) {
    val imageResource: Int = if (name == "LAMP") {
        if (state) R.drawable.lighton else R.drawable.lightoff
    } else if (name == "DOOR") {
        if (state) R.drawable.dooropen else R.drawable.doorclosed
    } else if (name == "WINDOW") {
        if (state) R.drawable.windowopen else R.drawable.windowclosed
    } else {
        0 // Default case
    }

        Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$name: ", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "$name state",
            modifier = Modifier.size(50.dp),
            colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color.Black)
        )
        Switch(checked = state, onCheckedChange = onToggle)
    }
}

@Composable
fun SpeechRecognitionSection(speechRecognition: SpeechRecognition) {
    var recognizedText by remember { mutableStateOf("") }

    Column {
        Button(
            onClick = {
                speechRecognition.startSpeechRecognition()
                // Implement callback or method to update recognizedText based on speech recognition results
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