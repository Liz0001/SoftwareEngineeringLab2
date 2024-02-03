package com.SoftwareEngineering.selab2.ui.screens

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.SoftwareEngineering.selab2.R
import com.SoftwareEngineering.selab2.data.States

@Composable
fun HomeScreen(
    vm: AppViewModel,
    statesUIStates: StatesUiStates,
    modifier: Modifier = Modifier
) {
    when (statesUIStates) {
        is StatesUiStates.Loading -> MessageScreen("...", modifier = modifier.fillMaxSize())
        is StatesUiStates.Success -> ViewScreen(vm, statesUIStates.states, modifier)
        is StatesUiStates.Error -> MessageScreen("Oh noo", modifier = modifier.fillMaxSize())
    }
}

@Composable
fun MessageScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = message)
    }
}

@Composable
fun ViewScreen(
    vm: AppViewModel,
    states: States,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
    ) {
        ControlRow("LAMP", states.lightOn) { vm.updateDatabase("lightOn", it) }

        ControlRow("DOOR", states.doorOpen) { vm.updateDatabase("doorOpen", it) }

        ControlRow("WINDOW", states.windowOpen) { vm.updateDatabase("windowOpen", it) }

        SpeechToText()
    }
}

@Composable
fun ControlRow(
    label: String,
    isChecked: Boolean?,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var state by remember { mutableStateOf(isChecked) }

        Row(
            modifier = Modifier
                .padding(vertical = 30.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$label: ",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            if (label == "LAMP") {
                Text(
                    text = if (state == true) "ON" else "OFF",
                    fontSize = 20.sp
                )
            } else {
                Text(
                    text = if (state == true) "OPEN" else "CLOSED",
                    fontSize = 20.sp
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 16.dp),
                painter = if (state == true) painterResource(getOnDrawable(label))
                else painterResource(getOffDrawable(label)),
                contentDescription = "",
                colorFilter = if (state == true) ColorFilter.tint(color = Color.Black)
                else ColorFilter.tint(color = Color.LightGray)
            )

            Switch(
                modifier = Modifier.semantics { contentDescription = "" },
                checked = state == true,
                onCheckedChange = {
                    state = it
                    onCheckedChange(it)
                }
            )
        }
    }
}

@DrawableRes
fun getOnDrawable(label: String): Int {
    return when (label) {
        "LAMP" -> R.drawable.lighton
        "DOOR" -> R.drawable.dooropen
        "WINDOW" -> R.drawable.windowopen
        else -> throw IllegalArgumentException("Invalid label: $label")
    }
}

@DrawableRes
fun getOffDrawable(label: String): Int {
    return when (label) {
        "LAMP" -> R.drawable.lightoff
        "DOOR" -> R.drawable.doorclosed
        "WINDOW" -> R.drawable.windowclosed
        else -> throw IllegalArgumentException("Invalid label: $label")
    }
}
